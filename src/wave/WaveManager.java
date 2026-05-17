package wave;

import balloon.Balloon;
import balloon.BlueBalloon;
import balloon.RedBalloon;
import balloon.YellowBalloon;
import balloon.LeadBallon;
import balloon.GreenBalloon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.Game;
import fri.shapesge.Manager;
import ui.status.GoldStatus;
import ui.status.HealthStatus;
import ui.status.WaveStatus;
import utils.ITickable;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

public class WaveManager implements ITickable {
    private static WaveManager instance;
    private ArrayList<Wave> allWaves;
    private final Manager manager;

    private final WaveStatus waveStatus;
    private final GoldStatus goldStatus;
    private final HealthStatus healthStatus;

    private int currentWaveIdx = 0;
    private int currentGroupIdx = 0;
    private int spawnedInGroup = 0;
    private int frameCounter = 0;

    private WaveManager() {
        this.allWaves = new ArrayList<>();
        this.manager = new Manager();
        this.waveStatus = WaveStatus.getInstance();
        this.goldStatus = GoldStatus.getInstance(Game.getGold());
        this.healthStatus = HealthStatus.getInstance(Game.getHealth());
    }

    /**
     * Vracia inštanciu singletonu WaveManager.
     * Ak ešte neexistuje, vytvorí sa nová.
     *
     * @return jediná inštancia WaveManager
     */
    public static WaveManager getInstance() {
        if (WaveManager.instance == null) {
            WaveManager.instance = new WaveManager();
        }
        return WaveManager.instance;
    }

    /**
     * Načíta definíciu vĺn zo JSON súboru v priečinku `res/assets/waves`.
     * Názov súboru sa predpokladá bez prípony (bez ".json").
     * Po načítaní sa WaveManager pridá do manažéra tvarov aby sa volalo jeho tick().
     *
     * @param filePath názov súboru s vlnami bez prípony
     */
    public void loadWaves(String filePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("res/assets/waves/" + filePath + ".json")) {
            Type arrayListType = new TypeToken<ArrayList<Wave>>() {
            }.getType();
            this.allWaves = gson.fromJson(reader, arrayListType);

            this.manager.stopManagingObject(this);
            this.manager.manageObject(this);
        } catch (IOException e) {
            System.err.println("Chyba pri čítaní JSON súboru!");
            e.printStackTrace();
        }
    }

    /**
     * Metóda volaná každý frame; spravuje postupné spawnovanie balónov podľa načítaných vĺn.
     */
    @Override
    public void tick() {
        if (this.allWaves == null || this.currentWaveIdx >= this.allWaves.size()) {
            return;
        }

        Wave currentWave = this.allWaves.get(this.currentWaveIdx);
        ArrayList<EnemyGroup> groups = currentWave.groups();

        if (this.currentGroupIdx < groups.size()) {
            EnemyGroup group = groups.get(this.currentGroupIdx);
            this.frameCounter++;

            if (this.frameCounter >= group.spacing()) {
                Optional<Balloon> spawnedBalloon = this.spawnBalloon(group.type());
                if (spawnedBalloon.isEmpty()) {
                    return;
                }

                Game.addBalloon(spawnedBalloon.get());

                this.spawnedInGroup++;
                this.frameCounter = 0;

                if (this.spawnedInGroup >= group.count()) {
                    this.currentGroupIdx++;
                    this.spawnedInGroup = 0;
                }
            }
        } else {
            this.currentWaveIdx++;
            this.changeWaveText();
            Game.addGold(currentWave.goldForWave());
            this.changeGoldText();
            this.currentGroupIdx = 0;
        }
    }

    private Optional<Balloon> spawnBalloon(String type) {
        Balloon balloon = switch (type) {
            case "RED" -> new RedBalloon();
            case "BLUE" -> new BlueBalloon();
            case "YELLOW" -> new YellowBalloon();
            case "GREEN" -> new GreenBalloon();
            case "LEAD" -> new LeadBallon();
            default -> null;
        };
        return balloon != null ? Optional.of(balloon) : Optional.empty();
    }

    private void changeWaveText() {
        this.waveStatus.setText(String.format("Wave: %d", this.currentWaveIdx));
    }

    private void changeGoldText() {
        this.goldStatus.setText("Gold: " + Game.getGold());
    }
}