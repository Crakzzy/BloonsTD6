package core;

import balloon.Balloon;
import fri.shapesge.Manager;
import map.MapLoader;
import monkey.DartMonkey;
import monkey.Monkey;
import monkey.MonkeySpawner;
import projectile.Projectile;
import projectile.dartMonkey.Dart;
import ui.GoldStatus;
import ui.HealthStatus;
import ui.WaveStatus;
import utils.Vector2D;
import wave.WaveManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Game {
    private GameMap map;
    private WaveManager waveManager;
    private WaveStatus waveStatus;
    private GoldStatus goldStatus;
    private HealthStatus healthStatus;
    private Manager manager;

    private static final ArrayList<Monkey> monkeys = new ArrayList<>();
    private static final ArrayList<Balloon> balloons = new ArrayList<>();
    private static final ArrayList<Projectile> projectiles = new ArrayList<>();

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

        this.manager = new Manager();
        this.manager.manageObject(this);
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

    public static List<Balloon> getBalloons() {
        return Collections.unmodifiableList(balloons);
    }

    public static void addProjectile(Dart dart) {
        Game.projectiles.add(dart);
    }

    public static void removeProjectile(Projectile projectile) {
        projectile.hide();
        Game.projectiles.remove(projectile);
    }

    public void place(int x, int y) {
        Optional<Monkey> monkey = MonkeySpawner.tryPlaceMonkey("dart", x, y);

        if (monkey.isEmpty()) return;
        Game.addMonkey(monkey.get());
    }

    public static void addMonkey(Monkey monkey) {
        monkeys.add(monkey);
    }

    public static void addBalloon(Balloon ballon) {
        balloons.add(ballon);
    }

    public static void forEachMonkey(Consumer<Monkey> action) {
        for (Monkey m : monkeys) {
            action.accept(m);
        }
    }

    public static void forEachBalloon(Consumer<Balloon> action) {
        for (Balloon b : balloons) {
            action.accept(b);
        }
    }

    public static boolean anyMonkey(Predicate<Monkey> condition) {
        return monkeys.stream().anyMatch(condition);
    }
}
