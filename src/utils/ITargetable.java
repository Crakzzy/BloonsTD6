package utils;

public interface ITargetable {
    int getX();
    int getY();
    void takeDamage(int damage);
    boolean isAlive();
}
