package projectile;

import utils.ITargetable;
import utils.Vector2D;

public class Bullet extends Projectile {
    /**
     * Vytvorí projektil s určeným poškodením, rýchlosťou a cieľom.
     *
     * @param initialPosition  počiatočná pozícia projektilu
     * @param target    cieľ, na ktorý sa projektil zameriava
     */
    public Bullet(Vector2D initialPosition, ITargetable target) {
        super(200, 50, "bullet", initialPosition, target);
    }

    /**
     * Aplikuje efekt na target - dvojnasobny damage
     * @param target cieľový objekt implementujúci ITargetable
     */
    @Override
    public void applyEffectTo(ITargetable target) {
        target.takeDamage(this.getDamage() * 2);
    }
}
