package map;

import core.GameMap;
import utils.Position;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class MapLoader {
    private static final String MAP_FOLDER = "res/maps/";

    /**
     * Načíta mapu z CSV súboru v priečinku `res/maps`.
     * Každý riadok CSV reprezentuje riadok dlaždíc a kódy sú prevedené cez `TileType.from`.
     *
     * @param mapName názov mapy bez prípony
     * @return Optional obsahujúci načítaný GameMap alebo empty pri chybe
     */
    public static Optional<GameMap> loadMap(String mapName) {
        String path = MAP_FOLDER + mapName + ".csv";

        try {
            String[] lines = Files.readString(Path.of(path)).split("\n");
            int rows = lines.length;
            int cols = lines[0].split(",").length;

            Tile[][] grid = new Tile[rows][cols];

            for (int row = 0; row < rows; row++) {
                String[] cells = lines[row].split(",");
                for (int col = 0; col < cols; col++) {
                    Position gridPos = new Position(row, col);
                    grid[row][col] = new Tile(TileType.from(cells[col].trim()), gridPos);
                }
            }
            GameMap.load(grid);
            return Optional.of(GameMap.getInstance());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load level. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return Optional.empty();
        }
    }


}
