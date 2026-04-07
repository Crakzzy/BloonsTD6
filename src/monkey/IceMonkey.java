package monkey;

import balloon.Balloon;
import utils.Vector2D;

public class IceMonkey extends Monkey {
    public IceMonkey(Vector2D position) {
        super(3, 300, 100, 1, "ice", position);
    }

    @Override
    public void shoot(Balloon target) {

    }
}
