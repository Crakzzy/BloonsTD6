package core;

import balloon.Balloon;
import fri.shapesge.Manager;
import map.MapLoader;
import monkey.Monkey;
import monkey.MonkeySpawner;
import projectile.Projectile;
import ui.pickers.MonkeyPickersFactory;
import ui.status.GoldStatus;
import ui.status.HealthStatus;
import ui.status.WaveStatus;
import wave.WaveManager;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Game {
    private GameMap map;

    private static WaveStatus waveStatus;
    private static GoldStatus goldStatus;
    private static HealthStatus healthStatus;

    private static final ArrayList<Monkey> MONKEYS = new ArrayList<>();
    private static final ArrayList<Balloon> BALLOONS = new ArrayList<>();
    private static final ArrayList<Projectile> PROJECTILES = new ArrayList<>();

    private static String currentlySelectedMonkey = null;

    private static int gold;
    private static int health;

    public Game() {
        Optional<GameMap> loadedMap = MapLoader.loadMap("1");
        loadedMap.ifPresent(gameMap -> this.map = gameMap);

        Game.gold = 500;
        Game.health = 150;

        waveStatus = WaveStatus.getInstance();
        goldStatus = GoldStatus.getInstance(Game.gold);
        healthStatus = HealthStatus.getInstance(Game.health);

        WaveManager waveManager = WaveManager.getInstance();
        waveManager.loadWaves("1");

        Manager manager = new Manager();
        manager.manageObject(this);

        MonkeyPickersFactory monkeyPickersFactory = MonkeyPickersFactory.getInstance();
        monkeyPickersFactory.createPickers();
    }

    public static void addGold(int amount) {
        Game.gold += amount;
        Game.goldStatus.setText("Gold: " + Game.gold);
    }

    public static int getGold() {
        return Game.gold;
    }

    public static void changeHealth(int newHealth) {
        Game.health = newHealth;
        Game.healthStatus.setText("Health: " + newHealth);
    }

    public static int getHealth() {
        return Game.health;
    }

    public static List<Balloon> getBalloons() {
        return Collections.unmodifiableList(BALLOONS);
    }

    public static void addProjectile(Projectile projectile) {
        Game.PROJECTILES.add(projectile);
    }

    public static void removeProjectile(Projectile projectile) {
        projectile.hide();
        Game.PROJECTILES.remove(projectile);
    }

    public static void removeBallon(Balloon balloon) {
        balloon.hide();
        balloon.stopManaging();
        Game.BALLOONS.remove(balloon);
    }

    public static void setCurrentlySelectedMonkey(String currentlySelectedMonkey) {
        Game.currentlySelectedMonkey = currentlySelectedMonkey;
    }

    public static void setGold(int gold) {
        Game.gold = gold;
        Game.goldStatus.setText("Gold: " + Game.gold);
    }

    public static void checkGameOver() {
        if (Game.health <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over! You lost all your health.");
            System.exit(0);
        }
    }

    public void click(int x, int y) {
        Optional<Monkey> monkey = MonkeySpawner.tryPlaceMonkey(Game.currentlySelectedMonkey, x, y);
        if (monkey.isEmpty()) {
            return;
        }
        Game.addMonkey(monkey.get());
    }

    public static void addMonkey(Monkey monkey) {
        MONKEYS.add(monkey);
    }

    public static void addBalloon(Balloon ballon) {
        BALLOONS.add(ballon);
    }

    public static boolean anyMonkey(Predicate<Monkey> condition) {
        return MONKEYS.stream().anyMatch(condition);
    }
}
