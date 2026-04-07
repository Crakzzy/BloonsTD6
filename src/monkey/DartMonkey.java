package monkey;

import balloon.Balloon;
import core.Game;
import projectile.dartMonkey.Dart;
import utils.Vector2D;

public class DartMonkey extends Monkey{
    public DartMonkey(Vector2D position) {
        super(5, 250, 100, 1,"dart", position);
    }

    @Override
    public void shoot(Balloon target) {
        Vector2D spawnPos = new Vector2D(this.getPosition().getX(), this.getPosition().getY());

        Dart dart = new Dart(spawnPos, target);

        Game.addProjectile(dart);
    }
}
