package map;

import fri.shapesge.Image;
import utils.Position;

public class Tile {
    private final TileType type;
    private final Image image;

    /**
     * Vytvorí dlaždicu s daným typom a umiestni jej obrázok na obrazovku.
     *
     * @param type typ dlaždice
     * @param position pozícia v mriežke (row,col)
     */
    public Tile(TileType type, Position position) {
        this.type = type;

        this.image = new Image(this.type.getImagePath());
        this.image.changePosition(position.getCol() * 64, position.getRow() * 64);
        this.image.makeVisible();
    }

    /**
     * Vracia typ tejto dlaždice.
     *
     * @return TileType pre túto dlaždicu
     */
    public TileType getType() {
        return this.type;
    }
}
