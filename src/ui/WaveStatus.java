package ui;

import utils.Vector2D;

public class WaveStatus extends Status {
    private static WaveStatus instance;

    private WaveStatus(int waveNumber) {
        super(new Vector2D(20, 1055), String.format("Wave: %d", waveNumber));
    }

    public static WaveStatus getInstance() {
        if (WaveStatus.instance == null) {
            WaveStatus.instance = new WaveStatus(1);
        }
        return WaveStatus.instance;
    }
}
