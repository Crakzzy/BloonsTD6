package ui;

import utils.Vector2D;

public class GoldStatus extends Status {
    private static GoldStatus instance;

    private GoldStatus(int gold) {
        super(new Vector2D(150, 1055), String.format("Gold: %d", gold));
    }

    public static GoldStatus getInstance(Integer gold) {
        if (GoldStatus.instance == null) {
            GoldStatus.instance = new GoldStatus(gold);
        }
        return GoldStatus.instance;
    }
}
