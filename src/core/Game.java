package core;

import map.MapLoader;
import wave.WaveManager;

import java.util.Optional;

public class Game {
    private GameMap map;
    private WaveManager waveManager;

    public Game() {
        Optional<GameMap> loadedMap = MapLoader.loadMap("1");
        loadedMap.ifPresent(gameMap -> this.map = gameMap);

        this.waveManager = WaveManager.getInstance();
        this.waveManager.loadWaves("1");
    }





}
