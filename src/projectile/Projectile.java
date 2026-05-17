package projectile;


import balloon.Balloon;
import core.Game;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.GameObject;
import utils.ITickable;
import utils.Vector2D;

public abstract class Projectile extends GameObject implements ITickable {
    private final int damage;
    private final int speed;
    private final Manager manager;
    private final Balloon target;

    /**
     * Vytvorí projektil s určeným poškodením, rýchlosťou a cieľom.
     *
     * @param damage množstvo poškodenia pri zásahu
     * @param speed rýchlosť projektilu (pixely za tick)
     * @param imageName názov obrázka projektilu (bez prípony)
     * @param position počiatočná pozícia projektilu
     * @param target cieľ, na ktorý sa projektil zameriava
     */
    public Projectile(int damage, int speed, String imageName, Vector2D position, Balloon target) {
        super(position, new Image("res/assets/projectiles/" + imageName + ".png"), 0);
        super.hide();
        this.damage = damage;
        this.speed = speed;
        this.target = target;

        this.manager = new Manager();
        this.manager.manageObject(this);
    }

    /**
     * Volané každý frame; vykonáva pohyb projektilu smerom na cieľ a kontrolu zásahu.
     */
    @Override
    public void tick() {
        if (this.target == null || !this.target.isAlive()) {
            Game.removeProjectile(this);
            return;
        }

        super.show();

        int currentAngleInt = this.rotateTowards(this.target.getPosition());

        double radians = Math.toRadians(currentAngleInt - 90);

        int dx = (int)Math.round(Math.cos(radians) * (double)this.speed);
        int dy = (int)Math.round(Math.sin(radians) * (double)this.speed);

        this.getPosition().add(dx, dy);

        this.getImage().changePosition(
                this.getPosition().getX() - 8,
                this.getPosition().getY() - 8
        );

        if (this.getPosition().distanceTo(this.target.getPosition()) < 25) {
            this.hasHitTarget(this.target);
        }
    }

    private void hasHitTarget(Balloon target) {
        target.takeDamage(this.damage);
        target.absorbEffect(this);
        this.manager.stopManagingObject(this);
        Game.removeProjectile(this);
    }

    /**
     * Vracia hodnotu poškodenia, ktorú tento projektil spôsobí pri zásahu.
     *
     * @return damage projektilu
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Aplikuje špeciálny efekt projektilu na balón (napr. jed alebo spomalenie).
     * Implementované v podtriedach.
     *
     * @param balloon cieľový balón
     */
    public abstract void applyEffectTo(Balloon balloon);
}
