package projectile;

import balloon.Balloon;
import utils.Vector2D;

public class Bullet extends Projectile {
    /**
     * Vytvorí projektil s určeným poškodením, rýchlosťou a cieľom.
     *
     * @param initialPosition  počiatočná pozícia projektilu
     * @param target    cieľ, na ktorý sa projektil zameriava
     */
    public Bullet(Vector2D initialPosition, Balloon target) {
        super(200, 50, "bullet", initialPosition, target);
    }

    @Override
    public void applyEffectTo(Balloon balloon) {
        balloon.takeDamage(this.getDamage() * 2);
    }
}
