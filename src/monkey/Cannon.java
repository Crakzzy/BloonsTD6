package monkey;

import balloon.Balloon;
import core.Game;
import projectile.Bomb;
import utils.Vector2D;

public class Cannon extends Monkey {
    public Cannon(Vector2D position) {
        super("cannon", position, MonkeyType.CANNON);
    }

    /**
     * Vytvorí výbuchový projektil (bombu) a pridá ho do hry.
     *
     * @param target cieľ, na ktorý sa má projektil zamerať
     */
    @Override
    public void shoot(Balloon target) {
        Vector2D spawnPos = new Vector2D(this.getPosition().getX(), this.getPosition().getY());

        Bomb projectile = new Bomb(spawnPos, target);

        Game.addProjectile(projectile);
    }
}