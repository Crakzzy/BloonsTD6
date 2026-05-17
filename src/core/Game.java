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

    /**
     * Pridá zadané množstvo zlata hráčovi a aktualizuje UI.
     *
     * @param amount množstvo zlata na pridanie (môže byť aj negatívne)
     */
    public static void addGold(int amount) {
        Game.gold += amount;
        Game.goldStatus.setText("Gold: " + Game.gold);
    }

    /**
     * Vracia aktuálne množstvo zlata hráča.
     *
     * @return množstvo zlata
     */
    public static int getGold() {
        return Game.gold;
    }

    /**
     * Nastaví novú hodnotu zdravia hráča a aktualizuje zobrazenie.
     *
     * @param newHealth nová hodnota zdravia
     */
    public static void changeHealth(int newHealth) {
        Game.health = newHealth;
        Game.healthStatus.setText("Health: " + newHealth);
    }

    /**
     * Vracia aktuálne zdravie hráča.
     *
     * @return hodnota zdravia
     */
    public static int getHealth() {
        return Game.health;
    }

    /**
     * Vracia nemodifikovateľný zoznam všetkých balónov v hre.
     *
     * @return zoznam balónov (read-only)
     */
    public static List<Balloon> getBalloons() {
        return Collections.unmodifiableList(BALLOONS);
    }

    /**
     * Pridá projektil do zoznamu aktívnych projektilov v hre.
     *
     * @param projectile projektil ktorý sa má pridať
     */
    public static void addProjectile(Projectile projectile) {
        Game.PROJECTILES.add(projectile);
    }

    /**
     * Odstráni projektil z hry a skryje jeho obrázok.
     *
     * @param projectile projektil ktorý sa má odstrániť
     */
    public static void removeProjectile(Projectile projectile) {
        projectile.hide();
        Game.PROJECTILES.remove(projectile);
    }

    /**
     * Odstráni balón z hry (skryje ho a zastaví jeho manažovanie).
     *
     * @param balloon balón ktorý sa má odstrániť
     */
    public static void removeBallon(Balloon balloon) {
        balloon.hide();
        balloon.stopManaging();
        Game.BALLOONS.remove(balloon);
    }

    /**
     * Nastaví typ opice, ktorý je momentálne vybraný v UI pre umiestnenie.
     *
     * @param currentlySelectedMonkey názov opice alebo null
     */
    public static void setCurrentlySelectedMonkey(String currentlySelectedMonkey) {
        Game.currentlySelectedMonkey = currentlySelectedMonkey;
    }

    /**
     * Nastaví aktuálne množstvo zlata hráča a aktualizuje UI.
     *
     * @param gold nová hodnota zlata
     */
    public static void setGold(int gold) {
        Game.gold = gold;
        Game.goldStatus.setText("Gold: " + Game.gold);
    }

    /**
     * Skontroluje stav hry a v prípade, že zdravie hráča je 0 alebo menej,
     * zobrazí hlášku o prehre a ukončí aplikáciu.
     */
    public static void checkGameOver() {
        if (Game.health <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over! You lost all your health.");
            System.exit(0);
        }
    }

    /**
     * Obsluha kliknutia používateľa v hernom okne: pokúsi sa umiestniť opicu
     * na dané súradnice a ak sa vytvorí, pridá ju do hry.
     *
     * @param x pixelová X súradnica kliknutia
     * @param y pixelová Y súradnica kliknutia
     */
    public void click(int x, int y) {
        Optional<Monkey> monkey = MonkeySpawner.tryPlaceMonkey(Game.currentlySelectedMonkey, x, y);
        if (monkey.isEmpty()) {
            return;
        }
        Game.addMonkey(monkey.get());
    }

    /**
     * Pridá opicu do herného zoznamu aktívnych opíc.
     *
     * @param monkey opica ktorá sa má pridať
     */
    public static void addMonkey(Monkey monkey) {
        MONKEYS.add(monkey);
    }

    /**
     * Pridá balón do hry.
     *
     * @param ballon balón ktorý sa má pridať
     */
    public static void addBalloon(Balloon ballon) {
        BALLOONS.add(ballon);
    }

    /**
     * Overí, či existuje aspoň jedna opica spĺňajúca danú podmienku.
     *
     * @param condition predikát testujúci opice
     * @return true ak aspoň jedna opica vyhovuje, inak false
     */
    public static boolean anyMonkey(Predicate<Monkey> condition) {
        return MONKEYS.stream().anyMatch(condition);
    }
}
