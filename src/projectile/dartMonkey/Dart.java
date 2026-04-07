package projectile.dartMonkey;

import balloon.Balloon;
import projectile.Projectile;
import utils.Vector2D;

public class Dart extends Projectile {
    public Dart(Vector2D initialPosition, Balloon target) {
        super(10, 4, "dart", initialPosition, target);
    }


}
