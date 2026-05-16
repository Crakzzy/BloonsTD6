package projectile;


import balloon.Balloon;
import core.Game;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.*;

public abstract class Projectile extends GameObject implements ITickable {
    private int damage;
    private int speed;
    private final Manager manager;
    private final Balloon target;

    public Projectile(int damage, int speed, String imageName, Vector2D position, Balloon target) {
        super(position, new Image("res/assets/projectiles/" + imageName + ".png"), 0);
        super.hide();
        this.damage = damage;
        this.speed = speed;
        this.target = target;

        this.manager = new Manager();
        this.manager.manageObject(this);
    }

    @Override
    public void tick() {
        if (this.target == null || !this.target.isAlive()) {
            Game.removeProjectile(this);
            return;
        }

        super.show();

        int currentAngleInt = this.rotateTowards(this.target.getPosition());

        double radians = Math.toRadians(currentAngleInt - 90);

        int dx = (int) Math.round(Math.cos(radians) * (double)this.speed);
        int dy = (int) Math.round(Math.sin(radians) * (double)this.speed);

        this.getPosition().add(dx, dy);

        this.getImage().changePosition(
                this.getPosition().getX() - 8,
                this.getPosition().getY() - 8
        );

        if (this.getPosition().distanceTo(target.getPosition()) < 25) {
            this.hasHitTarget(this.target);
        }
    }
    private void hasHitTarget(Balloon target) {
        target.takeDamage(this.damage);
        target.absorbEffect(this);
        this.manager.stopManagingObject(this);
        Game.removeProjectile(this);
    }

    public abstract void applyEffectTo(Balloon balloon);
}
