package projectile;

import balloon.Balloon;
import balloon.LeadBallon;
import utils.Vector2D;

public class Ice extends Projectile {
    public Ice(Vector2D position, Balloon target) {
        super(5, 20, "ice", position, target);
    }

    @Override
    public void applyEffectTo(Balloon balloon) {
        if (!(balloon instanceof LeadBallon)) {
            balloon.applyIceEffect();
        }
    }
}
