package balloon;

import controllers.MovementController;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.ITargetable;
import utils.ITickable;
import utils.Vector2D;
import core.GameMap;

import java.util.List;

public abstract class Balloon implements ITickable, ITargetable {
    private short speed;
    private int hp;
    private Vector2D position;
    private final Image image;
    private final Manager manager = new Manager();
    private final int goldForKill;

    private final List<Vector2D> waypoints;
    private int currentTargetIndex = 0;

    private static final MovementController movementController = MovementController.getInstance();

    public Balloon(short speed, int hp, String imageName, int goldForKill) {
        this.speed = speed;
        this.hp = hp;
        this.goldForKill = goldForKill;

        this.waypoints = GameMap.getInstance().generatePath();

        if (this.waypoints != null && !this.waypoints.isEmpty()) {
            this.position = this.waypoints.getFirst();
        } else {
            this.position = new Vector2D(0, 0);
        }

        this.image = new Image("res/assets/balloons/" + imageName + ".png");
        updateImagePosition();
        this.image.makeVisible();

        this.manager.manageObject(this);
    }

    @Override
    public void tick() {
        movementController.move(this);
    }

    public void updatePosition(Vector2D newPos) {
        this.position = newPos;
        updateImagePosition();
    }

    private void updateImagePosition() {
        if (this.image != null && this.position != null) {
            this.image.changePosition(this.position.getX() - 32, this.position.getY() - 32);
        }
    }

    public Vector2D getTarget() {
        if (waypoints != null && currentTargetIndex < waypoints.size()) {
            return waypoints.get(currentTargetIndex);
        }
        return null;
    }

    public void nextTarget() {
        this.currentTargetIndex++;
    }

    @Override
    public int getX() {
        return this.position.getX();
    }

    @Override
    public int getY() {
        return this.position.getY();
    }

    @Override
    public int[] getHitbox() {
        return new int[]{
                getX() - 32, getY() - 32, // Top-Left
                getX() + 32, getY() + 32  // Bottom-Right
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
            this.getImage().makeInvisible();
        }
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

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Image getImage() {
        return image;
    }

    public Manager getManager() {
        return manager;
    }
}