package monkey;

import balloon.Balloon;
import utils.Vector2D;

public class SniperMonkey extends Monkey {
    public SniperMonkey(Vector2D position) {
        super("sniper", position, MonkeyType.SNIPER);
    }

    @Override
    public void shoot(Balloon target) {

    }
}
