package monkey;

import balloon.Balloon;
import utils.ITickable;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Monkey implements ITickable {
    private int damage;
    private int cost;
    private short range;

    public abstract void shoot(Balloon target);

    public Optional<Balloon> findTarget(ArrayList<Balloon> balloons) {
        // TODO
        return Optional.empty();
    }

    public boolean isBallonInRange(Balloon balloon) {
        // TODO
        return true;
    }
}
