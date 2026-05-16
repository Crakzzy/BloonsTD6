package balloon;

import controllers.MovementController;
import core.Game;
import core.GameMap;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import map.Tile;
import map.TileType;
import projectile.Bomb;
import projectile.Dart;
import projectile.Ice;
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

    @Override
    public void tick() {
        movementController.move(this);
        if (poisonTick != 0) {
            poisonTick++;
        }

        if (poisonTick % 100 == 0 && isAlive()) {
            this.takeDamage(this.poisonTickDamage);
        } else if (!isAlive()) {
            this.poisonTick = 0;
            this.poisonTickDamage = 0;
        }
    }

    public Optional<Vector2D> getTarget() {
        if (waypoints != null && currentTargetIndex < waypoints.size()) {
            return Optional.ofNullable(waypoints.get(currentTargetIndex));
        }
        return Optional.empty();
    }

    public void nextTarget() {
        this.currentTargetIndex++;
    }

    @Override
    public int[] getHitbox() {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        return new int[]{
                x - 32, y - 32, // Top-Left
                x + 32, y + 32  // Bottom-Right
        };
    }

    @Override
    public boolean isAlive() {
        return this.hp > 0;
    }

    @Override
    public void takeDamage(int damage) {
        int nextHp = this.getHp() - damage;
        this.setHp(Math.max(nextHp, 0));

        if (!isAlive()) {
            Game.addGold(this.goldForKill);
            this.hide();
        }
    }

    @Override
    public void absorbEffect(Projectile projectile) {
        projectile.applyEffectTo(this);
    }

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

    public short getSpeed() {
        return speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpToTakeOnEnd() {
        return this.hpToTakeOnEnd;
    }

    public void stopManaging() {
        this.manager.stopManagingObject(this);
    }

    public void applyIceEffect() {
        this.speed /= 2;
    }

    public void applyPoisonDamage(int poisonDamage) {
        this.poisonTickDamage = poisonDamage;
        this.poisonTick++;
    }
}