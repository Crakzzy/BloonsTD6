package projectile;


import fri.shapesge.Image;
import utils.*;

public abstract class Projectile extends GameObject implements ITickable  {
    private int damage;
    private int speed;

    public Projectile(int damage, int speed, String imageName, Vector2D position, int angle) {
        super(position,  new Image("res/assets/projectiles/" + imageName + ".png"), angle);
        this.damage = damage;
        this.speed = speed;
    }

    @Override
    public void tick() {

    }
}
