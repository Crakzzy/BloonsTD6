package projectile;

import balloon.Balloon;
import balloon.LeadBallon;
import utils.Vector2D;

public class Ice extends Projectile {
    public Ice(Vector2D position, Balloon target) {
        super(5, 20, "ice", position, target);
    }

    /**
     * Aplikuje ľadový efekt na balón ak to nie je lead balón (spomalenie).
     *
     * @param balloon cieľový balón
     */
    @Override
    public void applyEffectTo(Balloon balloon) {
        if (!(balloon instanceof LeadBallon)) {
            balloon.applyIceEffect();
        }
    }
}
