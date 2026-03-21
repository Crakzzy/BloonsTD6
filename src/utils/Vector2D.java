package utils;

public class Vector2D {
    private final int x;
    private final int y;

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

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D scale(int factor) {
        return new Vector2D(this.x * factor, this.y * factor);
    }

    public Vector2D normalize() {
        int mag = this.magnitude();
        if (mag == 0) return new Vector2D(0, 0);
        return new Vector2D((int) ((int) this.x / mag), (int) ((int) this.y / mag));
    }

    public int magnitude() {
        return (int) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public int distanceTo(Vector2D other) {
        return this.subtract(other).magnitude();
    }

    public static Vector2D fromGrid(int row, int col, int tileSize) {
        return new Vector2D(
                (int) (col * tileSize + tileSize / 2f),
                (int) (row * tileSize + tileSize / 2f)
        );
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}