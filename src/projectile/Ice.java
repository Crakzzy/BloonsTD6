package projectile;

import balloon.Balloon;
import utils.Vector2D;

public class Ice extends Projectile {
    public Ice(Vector2D position, Balloon target) {
        super(5, 20, "ice", position, target);
    }
}
