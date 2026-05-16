package monkey;

import balloon.Balloon;
import core.Game;
import projectile.Dart;
import utils.Vector2D;

public class DartlingMonkey extends Monkey {
    public DartlingMonkey(Vector2D position) {
        super(20, 850, Integer.MAX_VALUE, 10, "dartling", position);
    }

    @Override
    public void shoot(Balloon target) {
        Vector2D spawnPos = new Vector2D(this.getPosition().getX(), this.getPosition().getY());

        Dart dart = new Dart(spawnPos, target);

        Game.addProjectile(dart);
    }
}
