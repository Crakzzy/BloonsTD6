package monkey;

import balloon.Balloon;
import core.Game;
import projectile.Ice;
import utils.Vector2D;

public class IceMonkey extends Monkey {
    public IceMonkey(Vector2D position) {
        super( "ice", position, MonkeyType.ICE);
    }

    /**
     * Vystrelí ľadový projektil, ktorý spomalí cieľ.
     *
     * @param target cieľový balón
     */
    @Override
    public void shoot(Balloon target) {
        Vector2D spawnPos = new Vector2D(this.getPosition().getX(), this.getPosition().getY());

        Ice projectile = new Ice(spawnPos, target);

        Game.addProjectile(projectile);
    }
}
