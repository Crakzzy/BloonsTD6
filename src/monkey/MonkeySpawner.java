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
            System.out.println("Tu nemôžeš stavať, je tu: " + terrain);
            return Optional.empty();
        }

        boolean collision = Game.anyMonkey(m -> m.wouldNewMonkeyConflict(pixelPos));
        if (collision) {
            System.out.println("Príliš blízko inej opice!");
            return Optional.empty();
        }

        return switch (name) {
            case "dart" -> Optional.of(new DartMonkey(pixelPos));
            default -> Optional.empty();
        };
    }
}
