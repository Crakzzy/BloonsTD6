package monkey;

import balloon.Balloon;
import core.Game;
import projectile.Dart;
import utils.Vector2D;

public class DartMonkey extends Monkey {
    public DartMonkey(Vector2D position) {
        super("dart", position, MonkeyType.DART);
    }

    /**
     * Vystrelí poison projektil (dart) na zadaný cieľ, ktorý môže aplikovať jed.
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
