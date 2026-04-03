package wave;

import java.util.ArrayList;

public class Wave {
    private int wave;
    private String description;
    private ArrayList<EnemyGroup> groups;

    public int getWaveNumber() {
        return this.wave;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<EnemyGroup> getGroups() {
        return this.groups;
    }
}