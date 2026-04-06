package utils;

public interface ITargetable {
    void takeDamage(int damage);
    boolean isAlive();
    int[] getHitbox();
}
