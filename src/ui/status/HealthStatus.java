package ui.status;

import utils.Vector2D;

public class HealthStatus extends Status {
    private static HealthStatus instance;

    private HealthStatus(int health) {
        super(new Vector2D(300, 1055), String.format("Health: %d", health));
    }

    /**
     * Vracia singleton pre zobrazenie zdravia.
     * Ak ešte neexistuje, vytvorí sa s počiatočnou hodnotou `health`.
     *
     * @param health počiatočná hodnota zdravia (používa sa len pri prvom volaní)
     * @return jediná inštancia HealthStatus
     */
    public static HealthStatus getInstance(int health) {
        if (HealthStatus.instance == null) {
            HealthStatus.instance = new HealthStatus(health);
        }
        return HealthStatus.instance;
    }
}
