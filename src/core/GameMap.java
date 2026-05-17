package core;

import map.Tile;
import map.TileType;
import utils.Vector2D;

import java.util.ArrayList;

public class GameMap {
    private static GameMap instance;
    private static Tile[][] grid;

    private GameMap(Tile[][] grid) {
        GameMap.grid = grid;
    }

    /**
     * Vracia inštanciu načítanej mapy.
     *
     * @return singleton GameMap
     * @throws IllegalStateException ak mapa ešte nebola načítaná
     */
    public static GameMap getInstance() {
        if (GameMap.instance == null) {
            throw new IllegalStateException("GameMap not loaded yet — call load() first");
        }
        return GameMap.instance;
    }

    /**
     * Načíta statickú mriežku dlaždíc (grid) do GameMap singletonu.
     *
     * @param grid 2D pole dlaždíc reprezentujúce mapu
     */
    public static void load(Tile[][] grid) {
        GameMap.instance = new GameMap(grid);
    }

    /**
     * Vygeneruje zoznam waypointov (stredy dlaždíc) pre cestu balónov podľa
     * typov dlaždíc (spawn/exit/rohy).
     *
     * @return zoznam súradníc (Vector2D) reprezentujúcich cestu
     */
    public ArrayList<Vector2D> generatePath() {
        ArrayList<Vector2D> path = new ArrayList<>();
        Tile[][] newGrid = getGrid();

        for (int r = 0; r < newGrid.length; r++) {
            for (int c = 0; c < newGrid[r].length; c++) {
                TileType type = newGrid[r][c].getType();

                if (type == TileType.SPAWN ||
                        type == TileType.BOTTOM_LEFT_CORNER ||
                        type == TileType.BOTTOM_RIGHT_CORNER ||
                        type == TileType.TOP_LEFT_CORNER ||
                        type == TileType.TOP_RIGHT_CORNER ||
                        type == TileType.EXIT) {

                    path.add(new Vector2D(c * 64 + 32, r * 64 + 32));
                }
            }
        }
        return path;
    }

    /**
     * Vracia internú mriežku dlaždíc mapy.
     *
     * @return 2D pole Tile objektov
     */
    public static Tile[][] getGrid() {
        return grid;
    }

    /**
     * Vracia typ dlaždice na zadaných indexoch; ak sú indexy mimo rozsahu,
     * vráti `TileType.EMPTY`.
     *
     * @param row index riadku
     * @param col index stĺpca
     * @return typ dlaždice alebo EMPTY ak indexy mimo rozsahu
     */
    public static TileType getTileType(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return TileType.EMPTY;
        }
        return grid[row][col].getType();
    }
}