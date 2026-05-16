package utils;

import projectile.Projectile;

public interface ITargetable {
    void takeDamage(int damage);
    void absorbEffect(Projectile projectile);
    boolean isAlive();
    int[] getHitbox();
}
