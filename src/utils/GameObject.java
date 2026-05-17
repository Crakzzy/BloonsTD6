package utils;

import balloon.Balloon;
import fri.shapesge.Image;

public abstract class GameObject {
    private Vector2D position;
    private final Image image;

    public GameObject(Vector2D position, Image image, int angle) {
        this.position = position;
        this.image = image;

        if (this.image != null) {
            this.image.changePosition(position.getX(), position.getY());
            this.image.changeAngle(angle);
            this.image.makeVisible();
        }
    }

    public Vector2D getPosition() {
        return this.position;
    }

    public void show() {
        this.image.makeVisible();
    }

    public void hide() {
        this.image.makeInvisible();
    }

    public void updatePosition(Vector2D newPosition) {
        if (this.image != null && this.position != null) {
            if (this instanceof Balloon) {
                this.image.changePosition(newPosition.getX() - 32, newPosition.getY() - 32);
            } else {
                this.image.changePosition(newPosition.getX(), newPosition.getY());
            }
            this.position = newPosition;
        }
    }

    public int rotateTowards(Vector2D targetPos) {
        double dx = targetPos.getX() - this.position.getX();
        double dy = targetPos.getY() - this.position.getY();

        double radians = Math.atan2(dy, dx);

        double degrees = Math.toDegrees(radians);

        int finalAngle = (int)Math.round(degrees) + 90;

        this.image.changeAngle(finalAngle);

        return finalAngle;
    }

    public Image getImage() {
        return this.image;
    }
}
