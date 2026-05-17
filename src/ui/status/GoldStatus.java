package ui.status;

import utils.Vector2D;

public class GoldStatus extends Status {
    private static GoldStatus instance;

    private GoldStatus(int gold) {
        super(new Vector2D(150, 1055), String.format("Gold: %d", gold));
    }

    /**
     * Vracia singleton pre zobrazenie zlata.
     * Ak ešte neexistuje, vytvorí sa s počiatočnou hodnotou `gold`.
     *
     * @param gold počiatočná hodnota zlata (používa sa len pri prvom volaní)
     * @return jediná inštancia GoldStatus
     */
    public static GoldStatus getInstance(Integer gold) {
        if (GoldStatus.instance == null) {
            GoldStatus.instance = new GoldStatus(gold);
        }
        return GoldStatus.instance;
    }
}
