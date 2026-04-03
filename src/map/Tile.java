package map;

import fri.shapesge.Image;
import monkey.Monkey;
import utils.Position;

public class Tile {
    private final TileType type;
    private Monkey monkey;
    private final Image image;
    private final Position position;

    public Tile(TileType type, Position position) {
        this.type = type;

        this.image = new Image(this.type.getImagePath());
        this.image.changePosition(position.getCol(), position.getRow());
        this.image.makeVisible();

        this.position = position;
    }

    public boolean moveToThisTileHorizontally() {
        return this.type == TileType.HORIZONTAL_PATH || this.type == TileType.EXIT;
    }

    public boolean placeMonkey(Monkey monkey) {
        if (this.type.canPlace() && this.monkey == null) {
            this.monkey = monkey;
            return true;
        }
        return false;
    }

    public TileType getType() {
        return this.type;
    }
}
