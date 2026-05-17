package utils;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void add(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public int magnitude() {
        return (int)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public int distanceTo(Vector2D other) {
        return this.subtract(other).magnitude();
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}