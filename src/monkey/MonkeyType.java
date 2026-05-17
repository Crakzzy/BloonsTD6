package monkey;

public enum MonkeyType {
    DART(5, 250, 100, 1),
    CANNON(100, 400, 100, 1),
    DARTLING(20, 850, Integer.MAX_VALUE, 10),
    ICE(3, 300, 100, 1),
    SNIPER(20, 350, Integer.MAX_VALUE, 2);

    private int damage;
    private int cost;
    private int range;
    private int shotsPerSecond;

    MonkeyType(int damage, int cost, int range, int shotsPerSecond) {
        this.damage = damage;
        this.cost = cost;
        this.range = range;
        this.shotsPerSecond = shotsPerSecond;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getShotsPerSecond() {
        return shotsPerSecond;
    }

    public void setShotsPerSecond(int shotsPerSecond) {
        this.shotsPerSecond = shotsPerSecond / 60;
    }
}
