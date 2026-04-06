package monkey;

import balloon.Balloon;
import utils.Vector2D;

public class DartMonkey extends Monkey{
    public DartMonkey(Vector2D position) {
        super(5, 250, 100, 1,"dart", position);
    }

    @Override
    public void shoot(Balloon target) {

    }
}
