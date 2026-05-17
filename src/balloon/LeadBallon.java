package balloon;

import core.Game;
import projectile.Bomb;
import projectile.Projectile;

public class LeadBallon extends Balloon {
    public LeadBallon() {
        super((short) 1, 1000, "lead", 500, 50);
    }

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
