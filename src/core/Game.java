package core;

import map.MapLoader;

import java.util.Optional;

public class Game {
    private GameMap map;

    public Game() {
        Optional<GameMap> loadedMap = MapLoader.loadMap("1");
        loadedMap.ifPresent(gameMap -> this.map = gameMap);
    }




}
