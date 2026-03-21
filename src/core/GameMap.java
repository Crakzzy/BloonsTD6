package core;

import map.Tile;

public class GameMap {
    private static GameMap instance;
    private Tile[][] grid;

    private GameMap(Tile[][] grid) {
        this.grid = grid;
    }

    public static GameMap getInstance() {
        if (GameMap.instance == null) {
            throw new IllegalStateException("GameMap not loaded yet — call load() first");
        }
        return GameMap.instance;
    }

    public static void load(Tile[][] grid) {
        GameMap.instance = new GameMap(grid);
    }
}