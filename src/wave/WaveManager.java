package wave;

import balloon.BlueBalloon;
import balloon.RedBalloon;
import balloon.YellowBalloon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.Game;
import fri.shapesge.Manager;
import ui.GoldStatus;
import ui.HealthStatus;
import ui.WaveStatus;
import utils.ITickable;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

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

    public static WaveManager getInstance() {
        if (WaveManager.instance == null) {
            WaveManager.instance = new WaveManager();
        }
        return WaveManager.instance;
    }

    public void loadWaves(String filePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("res/assets/waves/" + filePath + ".json")) {
            Type arrayListType = new TypeToken<ArrayList<Wave>>(){}.getType();
            this.allWaves = gson.fromJson(reader, arrayListType);

            this.manager.stopManagingObject(this);
            this.manager.manageObject(this);
        } catch (IOException e) {
            System.err.println("Chyba pri čítaní JSON súboru!");
            e.printStackTrace();
        }
    }

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
                this.spawnBalloon(group.type());
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

    private void spawnBalloon(String type) {
        switch (type) {
            case "RED" -> new RedBalloon();
            case "BLUE" -> new BlueBalloon();
            case "YELLOW" -> new YellowBalloon();
            default -> System.out.println("Neznámy typ: " + type);
        }
    }

    private void changeWaveText() {
        this.waveStatus.setText(String.format("Wave: %d", this.currentWaveIdx));
    }

    private void changeGoldText() {
        this.goldStatus.setText("Gold: " + Game.getGold());
    }

    private void changeHealthText() {
        this.healthStatus.setText("Health: " + Game.getHealth());
    }
}