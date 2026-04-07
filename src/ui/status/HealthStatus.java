package ui.status;

import utils.Vector2D;

public class HealthStatus extends Status {
    private static HealthStatus instance;

    private HealthStatus(int health) {
        super(new Vector2D(300, 1055), String.format("Health: %d", health));
    }

    public static HealthStatus getInstance(int health) {
        if (HealthStatus.instance == null) {
            HealthStatus.instance = new HealthStatus(health);
        }
        return HealthStatus.instance;
    }
}
