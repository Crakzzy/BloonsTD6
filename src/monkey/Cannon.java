package monkey;

import balloon.Balloon;
import utils.Vector2D;

public class Cannon extends Monkey {
    public Cannon(Vector2D position) {
        super(100, 400, 5, 1, "cannon", position);
    }

    @Override
    public void shoot(Balloon target) {

    }
}