package balloon;

import controllers.MovementController;
import core.Game;
import core.GameMap;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import map.Tile;
import map.TileType;
import projectile.Projectile;
import utils.GameObject;
import utils.ITargetable;
import utils.ITickable;
import utils.Vector2D;

import java.util.List;
import java.util.Optional;

public abstract class Balloon extends GameObject implements ITickable, ITargetable {
    private short speed;
    private int hp;
    private final Manager manager = new Manager();
    private final int goldForKill;

    private final List<Vector2D> waypoints;
    private int currentTargetIndex = 0;

    private static final MovementController movementController = MovementController.getInstance();
    private final int hpToTakeOnEnd;

    private int poisonTickDamage = 0;
    private int poisonTick = 0;


    public Balloon(short speed, int hp, String imageName, int goldForKill, int hpToTakeOnEnd) {
        Vector2D position;
        List<Vector2D> waypoints = GameMap.getInstance().generatePath();

        if (waypoints != null && !waypoints.isEmpty()) {
            position = waypoints.getFirst();
        } else {
            position = new Vector2D(0, 0);
        }

        super(position, new Image("res/assets/balloons/" + imageName + ".png"), 0);

        this.waypoints = waypoints;
        this.speed = speed;
        this.hp = hp;
        this.goldForKill = goldForKill;
        this.hpToTakeOnEnd = hpToTakeOnEnd;

        this.manager.manageObject(this);
    }

    /**
     * Aktualizuje stav balónu každý frame (pohyb a efekty a poison).
     */
    @Override
    public void tick() {
        Balloon.movementController.move(this);
        if (this.poisonTick != 0) {
            this.poisonTick++;
        }

        if (this.poisonTick % 100 == 0 && this.isAlive()) {
            this.takeDamage(this.poisonTickDamage);
        } else if (!isAlive()) {
            this.poisonTick = 0;
            this.poisonTickDamage = 0;
        }
    }

    /**
     * Vracia nasledujúci waypoint, ku ktorému sa balón momentálne presúva.
     *
     * @return Optional obsahujúci cieľovú pozíciu alebo empty ak žiadny nie je
     */
    public Optional<Vector2D> getTarget() {
        if (this.waypoints != null && this.currentTargetIndex < this.waypoints.size()) {
            return Optional.ofNullable(this.waypoints.get(this.currentTargetIndex));
        }
        return Optional.empty();
    }

    /**
     * Posunie interný index waypointu na ďalší cieľ.
     */
    public void nextTarget() {
        this.currentTargetIndex++;
    }

    /**
     * Vracia hitbox balónu vo formáte [left, top, right, bottom].
     *
     * @return pole súradníc hitboxu
     */
    @Override
    public int[] getHitbox() {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        return new int[]{
                x - 32, y - 32, // Top-Left
                x + 32, y + 32  // Bottom-Right
        };
    }

    /**
     * Zistí, či je balón stále nažive (HP &gt; 0).
     *
     * @return true ak HP &gt; 0, inak false
     */
    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    /**
     * Zníži HP balónu o zadanú hodnotu poškodenia.
     * Ak balón zomrie, pridá sa hráčovi odmena za zabitie a balón sa skryje.
     *
     * @param damage množstvo HP, ktoré sa má odpočítať
     */
    @Override
    public void takeDamage(int damage) {
        int nextHp = this.getHp() - damage;
        this.setHp(Math.max(nextHp, 0));
        if (!this.isAlive()) {
            Game.addGold(this.goldForKill);
            Game.removeBallon(this);
        }
    }

    /**
     * Varianta takeDamage, ktorá používa projektil ako zdroj poškodenia.
     *
     * @param projectile projektil, ktorého damage sa aplikuje
     */
    @Override
    public void takeDamage(Projectile projectile) {
        int nextHp = this.getHp() - projectile.getDamage();
        this.setHp(Math.max(nextHp, 0));
        if (!this.isAlive()) {
            Game.addGold(this.goldForKill);
            this.hide();
        }
    }

    /**
     * Aplikuje vedľajší efekt z projektilu na balón (napr. slow alebo poison).
     *
     * @param projectile zdroj efektu
     */
    @Override
    public void absorbEffect(Projectile projectile) {
        projectile.applyEffectTo(this);
    }

    /**
     * Zistí, či balón dosiahol cieľ (exit tile) na mape.
     *
     * @return true ak balón stojí na výstupnej dlaždici, inak false
     */
    public boolean hasReachedEnd() {
        int col = (this.getPosition().getX()) / 64;
        int row = (this.getPosition().getY()) / 64;

        Tile[][] mapTiles = GameMap.getGrid();

        if (row < 0 || row >= mapTiles.length || col < 0 || col >= mapTiles[0].length) {
            return false;
        }

        Tile currentTile = mapTiles[row][col];
        return currentTile != null && currentTile.getType() == TileType.EXIT;
    }

    /**
     * Vracia aktuálnu rýchlosť balónu.
     *
     * @return rýchlosť balónu
     */
    public short getSpeed() {
        return speed;
    }

    /**
     * Nastaví rýchlosť balónu.
     *
     * @param speed nová rýchlosť
     */
    public void setSpeed(short speed) {
        this.speed = speed;
    }

    /**
     * Vracia aktuálne HP balónu.
     *
     * @return HP hodnoty
     */
    public int getHp() {
        return hp;
    }

    /**
     * Nastaví HP balónu.
     *
     * @param hp nová hodnota HP
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Vracia množstvo HP, ktoré sa odoberie hráčovi, ak tento balón dôjde na koniec.
     *
     * @return poškodenie pre hráča pri dosiahnutí cieľa
     */
    public int getHpToTakeOnEnd() {
        return this.hpToTakeOnEnd;
    }

    /**
     * Zastaví manažovanie tohto balónu (napríklad pri jeho zničení).
     */
    public void stopManaging() {
        this.manager.stopManagingObject(this);
    }

    /**
     * Aplikuje slow efekt na balón
     */
    public void applyIceEffect() {
        this.speed /= 2;
    }

    /**
     * Spustí poison efekt na balóne, ktorý bude časom ubližovať.
     *
     * @param poisonDamage poškodenie na kazdy 100. tick
     */
    public void applyPoisonDamage(int poisonDamage) {
        this.poisonTickDamage = poisonDamage;
        this.poisonTick++;
    }

    /**
     * Vracia množstvo zlata, ktoré hráč získa za zabitie tohto balónu.
     *
     * @return odmena za zabitie
     */
    public int getGoldForKill() {
        return this.goldForKill;
    }
}