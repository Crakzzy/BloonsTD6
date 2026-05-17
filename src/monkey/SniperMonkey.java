package monkey;

import balloon.Balloon;
import core.Game;
import projectile.Dart;
import utils.Vector2D;

public class SniperMonkey extends Monkey {
    public SniperMonkey(Vector2D position) {
        super("sniper", position, MonkeyType.SNIPER);
    }

    /**
     * Vystrelí presný výstrel na cieľ
     *
     * @param target cieľový balón
     */
    @Override
    public void shoot(Balloon target) {
        Vector2D spawnPos = new Vector2D(this.getPosition().getX(), this.getPosition().getY());

        Dart dart = new Dart(spawnPos, target, 5);

        Game.addProjectile(dart);
    }
}
