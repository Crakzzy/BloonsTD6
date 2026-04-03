package balloon;

public class RedBalloon extends Balloon {
    public RedBalloon() {
        super((short) 4, 20, "res/assets/balloons/red.png");
    }

    @Override
    public void takeDamage(int damage) {
        int nextHp = this.getHp() - damage;
        this.setHp(Math.max(nextHp, 0));

        if (!isAlive()) {
            this.getImage().makeInvisible();
        }
    }

    @Override
    public boolean isAlive() {
        return this.getHp() > 0;
    }
}