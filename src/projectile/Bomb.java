package projectile;

import balloon.Balloon;
import utils.Vector2D;

public class Bomb extends Projectile {
    public Bomb(Vector2D position, Balloon target) {
        super(100, 10, "bomb", position, target);
    }

    public int getExplosionDamage() {
        return 100;
    }

    @Override
    public void applyEffectTo(Balloon balloon) {
        balloon.takeDamage(this.getExplosionDamage());
    }
}
