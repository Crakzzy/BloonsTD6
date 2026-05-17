package map;

public enum TileType {
    GRASS("grass.png", true),
    HORIZONTAL_PATH("path_horizontal.png", false),
    VERTICAL_PATH("path_vertical.png", false),
    WATER("grass.png", false),
    SPAWN("spawn.png", false),
    EXIT("end.png", false),
    BOTTOM_LEFT_CORNER("path_vertical.png", false),
    BOTTOM_RIGHT_CORNER("path_vertical.png", false),
    TOP_LEFT_CORNER("path_vertical.png", false),
    TOP_RIGHT_CORNER("path_vertical.png", false),
    EMPTY("empty.png", false);

    private final String imagePath;
    private final boolean placeableOn;

    TileType(String imagePath, boolean placeableOn) {
        this.imagePath = imagePath;
        this.placeableOn = placeableOn;
    }

    /**
     * Vracia cestu k obrázku pre daný typ dlaždice.
     *
     * @return relatívna cesta k obrázku v `res/assets/map`
     */
    public String getImagePath() {
        return "res/assets/map/" + this.imagePath;
    }

    /**
     * Indikuje, či je možné na túto dlaždicu umiestniť opicu (placeable).
     *
     * @return true ak je možné umiestniť opicu, false inak
     */
    public boolean canPlace() {
        return this.placeableOn;
    }

    /**
     * Vytvorí TileType z kódu použitom v CSV mape.
     *
     * @param code kód dlaždice (napr. "G", "PH", ...)
     * @return zodpovedajúci TileType
     * @throws IllegalArgumentException ak je kód neznámy
     */
    public static TileType from(String code) {
        return switch (code) {
            case "G" -> GRASS;
            case "PH" -> HORIZONTAL_PATH;
            case "PV" -> VERTICAL_PATH;
            case "CBL" -> BOTTOM_LEFT_CORNER;
            case "CBR" -> BOTTOM_RIGHT_CORNER;
            case "CTL" -> TOP_LEFT_CORNER;
            case "CTR" -> TOP_RIGHT_CORNER;
            case "W" -> WATER;
            case "S" -> SPAWN;
            case "X" -> EMPTY;
            case "E" -> EXIT;
            default -> throw new IllegalArgumentException("Unknown tile: " + code);
        };
    }
}
