package projectile;

import utils.ITargetable;
import utils.Vector2D;

public class Bomb extends Projectile {
    public Bomb(Vector2D position, ITargetable target) {
        super(100, 10, "bomb", position, target);
    }

    /**
     * Vracia základné explozné poškodenie bomby.
     *
     * @return množstvo poškodenia pri explózii
     */
    public int getExplosionDamage() {
        return 100;
    }

    /**
     * Aplikuje explozný efekt na zasiahnuty balón
     *
     * @param target cieľový target
     */
    @Override
    public void applyEffectTo(ITargetable target) {
        target.takeDamage(this.getExplosionDamage());
    }
}
