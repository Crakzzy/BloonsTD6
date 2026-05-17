package projectile;

import balloon.Balloon;
import balloon.LeadBallon;
import utils.ITargetable;
import utils.Vector2D;

public class Ice extends Projectile {
    public Ice(Vector2D position, ITargetable target) {
        super(5, 20, "ice", position, target);
    }

    /**
     * Aplikuje ľadový efekt na target ak to nie je lead balón (spomalenie).
     *
     * @param target cieľový target
     */
    @Override
    public void applyEffectTo(ITargetable target) {
        if (!(target instanceof LeadBallon)) {
            ((Balloon)target).applyIceEffect();
        }
    }
}
