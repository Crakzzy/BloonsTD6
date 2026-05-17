package monkey;

import balloon.Balloon;
import core.Game;
import projectile.Dart;
import utils.Vector2D;

public class DartlingMonkey extends Monkey {
    public DartlingMonkey(Vector2D position) {
        super("dartling", position, MonkeyType.DARTLING);
    }

    @Override
    public void shoot(Balloon target) {
        Vector2D spawnPos = new Vector2D(this.getPosition().getX(), this.getPosition().getY());

        Dart dart = new Dart(spawnPos, target, 2);

        Game.addProjectile(dart);
    }
}
