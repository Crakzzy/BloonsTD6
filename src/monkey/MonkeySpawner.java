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

        MonkeyType monkeyType = switch (name.toLowerCase()) {
            case "dart" -> MonkeyType.DART;
            case "ice" -> MonkeyType.ICE;
            case "cannon" -> MonkeyType.CANNON;
            case "sniper" -> MonkeyType.SNIPER;
            case "dartling" -> MonkeyType.DARTLING;
            default -> null;
        };

        if (monkeyType == null) {
            return Optional.empty();
        }

        if (monkeyType.getCost() > Game.getGold()) {
            return Optional.empty();
        }

        Game.setGold(Game.getGold() - monkeyType.getCost());

        return switch (monkeyType) {
            case DART -> Optional.of(new DartMonkey(pixelPos));
            case ICE -> Optional.of(new IceMonkey(pixelPos));
            case CANNON -> Optional.of(new Cannon(pixelPos));
            case SNIPER -> Optional.of(new SniperMonkey(pixelPos));
            case DARTLING -> Optional.of(new DartlingMonkey(pixelPos));
        };
    }
}
