package ui.status;

import utils.Vector2D;

public class WaveStatus extends Status {
    private static WaveStatus instance;

    private WaveStatus(int waveNumber) {
        super(new Vector2D(20, 1055), String.format("Wave: %d", waveNumber));
    }

    /**
     * Vracia singleton pre zobrazenie čísla vlny. Pri prvom volaní vytvorí inštanciu s hodnotou 1.
     *
     * @return jediná inštancia WaveStatus
     */
    public static WaveStatus getInstance() {
        if (WaveStatus.instance == null) {
            WaveStatus.instance = new WaveStatus(1);
        }
        return WaveStatus.instance;
    }
}
