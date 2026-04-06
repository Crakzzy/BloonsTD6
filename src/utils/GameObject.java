package utils;

import balloon.Balloon;
import fri.shapesge.Image;

public abstract class GameObject {
    private Vector2D position;
    private final Image image;
    private int angle;

    public GameObject(Vector2D position, Image image, int angle) {
        this.position = position;
        this.image = image;
        this.angle = angle;

        if (this.image != null) {
            this.image.changePosition(position.getX(), position.getY());
            this.image.changeAngle(angle);
            this.image.makeVisible();
        }
    }

    public Vector2D getPosition() {
        return this.position;
    }

    public void changeAngle(int newAngle) {
        this.angle = newAngle;
        this.image.changeAngle(newAngle);
        this.show();
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

    public Image getImage() {
        return this.image;
    }
}
