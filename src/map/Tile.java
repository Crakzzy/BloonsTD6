package map;

import fri.shapesge.Image;
import utils.Position;

public class Tile {
    private final TileType type;
    private final Image image;

    public Tile(TileType type, Position position) {
        this.type = type;

        this.image = new Image(this.type.getImagePath());
        this.image.changePosition(position.getCol() * 64, position.getRow() * 64);
        this.image.makeVisible();
    }

    public TileType getType() {
        return this.type;
    }
}
