package monkey;

import balloon.Balloon;
import core.Game;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.GameObject;
import utils.ITickable;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Monkey extends GameObject implements ITickable {
    private final int damage;
    private final int cost;
    private final int range;
    private int ticksBetweenShots;
    private int currentCooldownBetweenShots = 0;
    private Manager manager;

    private static final int MINIMAL_MONKEY_MARGIN_PX = 1;

    public Monkey(int damage, int cost, int range, int shotsPerSecond, String imageName, Vector2D position) {
        super(position, new Image("res/assets/monkeys/" + imageName + ".png"), 0);
        this.damage = damage;
        this.cost = cost;
        this.range = range;
        this.ticksBetweenShots = 60 / shotsPerSecond;

        this.manager = new Manager();
        this.manager.manageObject(this);
    }

    @Override
    public void tick() {
        Optional<Balloon> target = this.findTarget(Game.getBalloons());
        System.out.println("Balloons size " + Game.getBalloons().size());

        if (target.isPresent()) {
            this.rotateTowards(target.get().getPosition());

            if (this.currentCooldownBetweenShots > 0) {
                this.currentCooldownBetweenShots--;
            } else {
                this.shoot(target.get());
                this.currentCooldownBetweenShots = this.ticksBetweenShots;
            }
        } else if (this.currentCooldownBetweenShots > 0) {
            this.currentCooldownBetweenShots--;
        }
    }

    public abstract void shoot(Balloon target);

    public boolean wouldNewMonkeyConflict(Vector2D newPos) {
        double dx = this.getPosition().getX() - newPos.getX();
        double dy = this.getPosition().getY() - newPos.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < 40;
    }

    public void setShotsPerSecond(int newShotsPerSecond) {
        this.ticksBetweenShots = 60 / newShotsPerSecond;
    }

    private Optional<Balloon> findTarget(List<Balloon> balloons) {
        for (Balloon balloon : balloons) {
            if (this.isBalloonInRange(balloon)) {
                return Optional.of(balloon);
            }
        }
        return Optional.empty();
    }

    private boolean isBalloonInRange(Balloon balloon) {
        Vector2D balloonPosition = balloon.getPosition();

        double dx = this.getPosition().getX() - balloonPosition.getX();
        double dy = this.getPosition().getY() - balloonPosition.getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance <= this.range;
    }
}
