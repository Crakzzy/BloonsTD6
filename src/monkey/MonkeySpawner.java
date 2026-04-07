package monkey;

import core.Game;
import core.GameMap;
import map.TileType;
import utils.Vector2D;

import java.util.Optional;

public class MonkeySpawner {
    public static Optional<Monkey> tryPlaceMonkey(String name, int x, int y) {
        Vector2D pixelPos = new Vector2D(x - 32, y - 32);

        int gridCol = x / 64;
        int gridRow = y / 64;

        TileType terrain = GameMap.getTileType(gridRow, gridCol);

        if (!terrain.canPlace()) {
            return Optional.empty();
        }

        boolean collision = Game.anyMonkey(m -> m.wouldNewMonkeyConflict(pixelPos));
        if (collision) {
            return Optional.empty();
        }

        return switch (name) {
            case "dart" -> Optional.of(new DartMonkey(pixelPos));
            case "ice" -> Optional.of(new IceMonkey(pixelPos));
            case "cannon" -> Optional.of(new Cannon(pixelPos));
            case "sniper" -> Optional.of(new SniperMonkey(pixelPos));
            case "dartling" -> Optional.of(new DartlingMonkey(pixelPos));
            default -> Optional.empty();
        };
    }
}
