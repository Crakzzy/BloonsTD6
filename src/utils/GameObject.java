package utils;

import balloon.Balloon;
import fri.shapesge.Image;

public abstract class GameObject {
    private Vector2D position;
    private final Image image;

    /**
     * Vytvorí herný objekt s počiatočnou pozíciou a obrázkom.
     *
     * @param position počiatočná pozícia
     * @param image obrázok reprezentujúci objekt
     * @param angle počiatočný uhol obrázku
     */
    public GameObject(Vector2D position, Image image, int angle) {
        this.position = position;
        this.image = image;

        if (this.image != null) {
            this.image.changePosition(position.getX(), position.getY());
            this.image.changeAngle(angle);
            this.image.makeVisible();
        }
    }

    /**
     * Vracia aktuálnu pozíciu objektu.
     *
     * @return pozícia objektu
     */
    public Vector2D getPosition() {
        return this.position;
    }

    /**
     * Zobrazí obrazok objektu.
     */
    public void show() {
        this.image.makeVisible();
    }

    /**
     * Skryje obrazok objektu.
     */
    public void hide() {
        this.image.makeInvisible();
    }

    /**
     * Aktualizuje pozíciu objektu a premiestni jeho obrázok.
     * Pri balónikoch sa používa offset, aby bol obrázok centrovaný.
     *
     * @param newPosition nová pozícia
     */
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

    /**
     * Otočí obrázok smerom na zadanú pozíciu a vráti konečný uhol.
     *
     * @param targetPos cieľová pozícia
     * @return uhol (v stupňoch) na ktorý bol objekt otočený
     */
    public int rotateTowards(Vector2D targetPos) {
        double dx = targetPos.getX() - this.position.getX();
        double dy = targetPos.getY() - this.position.getY();

        double radians = Math.atan2(dy, dx);

        double degrees = Math.toDegrees(radians);

        int finalAngle = (int)Math.round(degrees) + 90;

        this.image.changeAngle(finalAngle);

        return finalAngle;
    }

    /**
     * Vracia referenciu na Image objekt používaný pre vykresľovanie.
     *
     * @return Image pre tento GameObject
     */
    public Image getImage() {
        return this.image;
    }
}
