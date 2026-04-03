package wave;

import balloon.RedBalloon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fri.shapesge.Manager;
import utils.ITickable;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WaveManager implements ITickable {
    private static WaveManager instance;
    private ArrayList<Wave> allWaves;
    private Manager manager;

    private int currentWaveIdx = 0;
    private int currentGroupIdx = 0;
    private int spawnedInGroup = 0;
    private int frameCounter = 0;

    private WaveManager() {
        this.allWaves = new ArrayList<>();
        this.manager = new Manager();
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
        ArrayList<EnemyGroup> groups = currentWave.getGroups();

        if (this.currentGroupIdx < groups.size()) {
            EnemyGroup group = groups.get(this.currentGroupIdx);
            this.frameCounter++;

            if (this.frameCounter >= group.getSpacing()) {
                this.spawnBalloon(group.getType());
                this.spawnedInGroup++;
                this.frameCounter = 0;

                if (this.spawnedInGroup >= group.getCount()) {
                    this.currentGroupIdx++;
                    this.spawnedInGroup = 0;
                }
            }
        } else {
            // here, if i want to use start btn
            this.currentWaveIdx++;
            this.currentGroupIdx = 0;
        }
    }

    private void spawnBalloon(String type) {
        switch (type) {
            case "RED" -> new RedBalloon();
            // case "BLUE" ->
            // case "LEAD" ->
            default -> System.out.println("Neznámy typ: " + type);
        }
    }
}