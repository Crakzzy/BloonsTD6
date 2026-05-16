package projectile;

import balloon.Balloon;
import utils.Vector2D;

public class Dart extends Projectile {

    private final int poisonDamage;

    public Dart(Vector2D initialPosition, Balloon target, int poisonDamage) {
        super(10, 4, "dart", initialPosition, target);
        this.poisonDamage = poisonDamage;
    }


    public int getPoisonDamage() {
        return poisonDamage;
    }
}
