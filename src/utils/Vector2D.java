package utils;

/*Vygenerovana class pomocou umelej inteligencie*/

public class Vector2D {
    private int x;
    private int y;

    /**
     * Vytvorí nový vektor so zadanými súradnicami.
     *
     * @param x súradnica X v pixeloch
     * @param y súradnica Y v pixeloch
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Vracia X súradnicu vektora.
     *
     * @return X súradnica (pixely)
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vracia Y súradnicu vektora.
     *
     * @return Y súradnica (pixely)
     */
    public int getY() {
        return this.y;
    }

    /**
     * Posunie tento vektor o daný posun v oboch osiach.
     *
     * @param dx posun v osi X
     * @param dy posun v osi Y
     */
    public void add(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Vráti nový vektor, ktorý je súčtom tohto vektora a zadaného vektora.
     *
     * @param other vektor, ktorý sa sčíta
     * @return nový vektor (súčet)
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Vráti nový vektor predstavujúci rozdiel tohto vektora a zadaného vektora.
     *
     * @param other vektor, ktorý sa odčíta
     * @return nový vektor (rozdiel)
     */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    /**
     * Vypočíta veľkosť (dĺžku) vektora.
     *
     * @return dĺžka vektora (pixely)
     */
    public int magnitude() {
        return (int)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Vypočíta vzdialenosť medzi týmto vektorom a zadaným vektorom.
     *
     * @param other cieľový vektor
     * @return vzdialenosť (pixely)
     */
    public int distanceTo(Vector2D other) {
        return this.subtract(other).magnitude();
    }

    @Override
    /*
     * Vracia textovú reprezentáciu vektora vo formáte "(x, y)".
     *
     * @return textová reprezentácia vektora
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}