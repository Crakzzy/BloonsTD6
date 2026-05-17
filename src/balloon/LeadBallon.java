package balloon;

import core.Game;
import projectile.Bomb;
import projectile.Projectile;

public class LeadBallon extends Balloon {
    /**
     * Vytvorí lead balón s vysokým HP a odolnosťou voči väčšine efektov.
     */
    public LeadBallon() {
        super((short)1, 1000, "lead", 500, 50);
    }

    /**
     * Override takeDamage pre prípady, keď je kanon (bomba) zdrojom poškodenia.
     * Lead balón prijíma poškodenie len od bômb.
     *
     * @param projectile projektil spôsobujúci poškodenie
     */
    @Override
    public void takeDamage(Projectile projectile) {
        if (projectile instanceof Bomb) {
            int nextHp = this.getHp() - projectile.getDamage();
            this.setHp(Math.max(nextHp, 0));

            if (!isAlive()) {
                Game.addGold(super.getGoldForKill());
                this.hide();
            }
        }
    }
}
