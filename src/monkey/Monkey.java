package monkey;

import balloon.Ballon;
import utils.ITickable;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Monkey implements ITickable {
    protected int damage;
    protected int cost;
    protected short range;

    public abstract void shoot(Ballon target);

    public Optional<Ballon> findTarget(ArrayList<Ballon> ballons) {
        // TODO
        return Optional.empty();
    }

    public boolean isBallonInRange(Ballon ballon) {
        // TODO
        return true;
    }
}
