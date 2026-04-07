package monkey;

import balloon.Balloon;
import utils.Vector2D;

public class SniperMonkey extends Monkey {
    public SniperMonkey(Vector2D position) {
        super(20, 350, 9999, 1, "sniper", position);
    }

    @Override
    public void shoot(Balloon target) {

    }
}
