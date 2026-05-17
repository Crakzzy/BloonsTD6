package projectile;

import balloon.Balloon;
import balloon.LeadBallon;
import utils.Vector2D;

public class Dart extends Projectile {

    private final int poisonDamage;

    /**
     * Vytvori Dart projektil
     * @param initialPosition pozicia
     * @param target cielovy balon
     * @param poisonDamage poison damage
     */
    public Dart(Vector2D initialPosition, Balloon target, int poisonDamage) {
        super(10, 4, "dart", initialPosition, target);
        this.poisonDamage = poisonDamage;
    }


    /**
     * Vracia hodnotu jedovitého poškodenia, ktoré tento darty aplikuje cez čas.
     *
     * @return poškodenie jedu na tick
     */
    public int getPoisonDamage() {
        return this.poisonDamage;
    }

    /**
     * Aplikuje efekt dartu (poison) na cieľ, ak cieľ nie je lead balón.
     *
     * @param balloon cieľový balón
     */
    @Override
    public void applyEffectTo(Balloon balloon) {
        if (!(balloon instanceof LeadBallon)) {
            balloon.applyPoisonDamage(this.getPoisonDamage());
        }
    }

}
