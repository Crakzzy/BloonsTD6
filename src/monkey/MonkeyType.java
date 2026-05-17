package monkey;

public enum MonkeyType {
    DART(5, 250, 100, 1),
    CANNON(100, 400, 100, 1),
    DARTLING(20, 850, Integer.MAX_VALUE, 10),
    ICE(3, 300, 100, 1),
    SNIPER(20, 350, Integer.MAX_VALUE, 2);

    private int damage;
    private final int cost;
    private final int range;
    private final int shotsPerSecond;

    MonkeyType(int damage, int cost, int range, int shotsPerSecond) {
        this.damage = damage;
        this.cost = cost;
        this.range = range;
        this.shotsPerSecond = shotsPerSecond;
    }
    /**
     * Vracia cenu opice v zlatých.
     *
     * @return cena opice
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Vracia dosah streľby pre daný typ opice (v pixeloch).
     *
     * @return dosah opice
     */
    public int getRange() {
        return this.range;
    }

    /**
     * Vracia počet rán za sekundu pre daný typ opice.
     *
     * @return rýchlosť streľby (shots per second)
     */
    public int getShotsPerSecond() {
        return this.shotsPerSecond;
    }
}
