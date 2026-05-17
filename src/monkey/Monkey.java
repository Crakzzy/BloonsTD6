package monkey;

import balloon.Balloon;
import core.Game;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.GameObject;
import utils.ITickable;
import utils.Vector2D;

import java.util.List;
import java.util.Optional;

public abstract class Monkey extends GameObject implements ITickable {
    private int ticksBetweenShots;
    private int currentCooldownBetweenShots = 0;
    private Manager manager;
    private MonkeyType type;

    private static final int MINIMAL_MONKEY_MARGIN_PX = 1;

    public Monkey(String imageName, Vector2D position, MonkeyType type) {
        super(position, new Image("res/assets/monkeys/" + imageName + ".png"), 0);
        this.type = type;
        this.ticksBetweenShots = 60 / type.getShotsPerSecond();

        this.manager = new Manager();
        this.manager.manageObject(this);
    }

    @Override
    public void tick() {
        if (this.currentCooldownBetweenShots > 0) {
            this.currentCooldownBetweenShots--;
        }

        Optional<Balloon> target = this.findTarget(Game.getBalloons());

        if (target.isPresent()) {
            this.rotateTowards(target.get().getPosition());

            if (this.currentCooldownBetweenShots == 0) {
                this.shoot(target.get());
                this.currentCooldownBetweenShots = this.ticksBetweenShots;
            }
        }
    }

    public abstract void shoot(Balloon target);

    public boolean wouldNewMonkeyConflict(Vector2D newPos) {
        double dx = this.getPosition().getX() - newPos.getX();
        double dy = this.getPosition().getY() - newPos.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < 40;
    }

    private Optional<Balloon> findTarget(List<Balloon> balloons) {
        for (Balloon balloon : balloons) {
            if (balloon.isAlive() && this.isBalloonInRange(balloon) && !balloon.hasReachedEnd()) {
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

        return distance <= this.type.getRange();
    }
}
