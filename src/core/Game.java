package core;

import map.MapLoader;
import ui.GoldStatus;
import ui.HealthStatus;
import ui.WaveStatus;
import wave.WaveManager;

import java.util.Optional;

public class Game {
    private GameMap map;
    private WaveManager waveManager;
    private WaveStatus waveStatus;
    private GoldStatus goldStatus;
    private HealthStatus healthStatus;

    private static int gold;
    private static int health;

    public Game() {
        Optional<GameMap> loadedMap = MapLoader.loadMap("1");
        loadedMap.ifPresent(gameMap -> this.map = gameMap);

        Game.gold = 500;
        Game.health = 150;

        this.waveStatus = WaveStatus.getInstance();
        this.goldStatus = GoldStatus.getInstance(Game.gold);
        this.healthStatus = HealthStatus.getInstance(Game.health);

        this.waveManager = WaveManager.getInstance();
        this.waveManager.loadWaves("1");
    }

    public static void addGold(int amount) {
        Game.gold += amount;
    }

    public static int getGold() {
        return Game.gold;
    }

    public static void changeHealth(int newHealth) {
        Game.health = newHealth;
    }

    public static int getHealth() {
        return Game.gold;
    }
}
