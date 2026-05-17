package ui.status;

import fri.shapesge.FontStyle;
import fri.shapesge.TextBlock;
import utils.Vector2D;

public abstract class Status {
    private final TextBlock textBlock;
    private final Vector2D position;

    /**
     * Vytvorí stavový text (UI) na danom mieste s počiatočným labelom.
     *
     * @param position pozícia textu
     * @param label počiatočný text
     */
    public Status(Vector2D position, String label) {
        this.position = position;
        this.textBlock = new TextBlock(label, this.position.getX(), this.position.getY());
        this.textBlock.changeFont("Arial", FontStyle.BOLD, 24);
        this.textBlock.makeVisible();
    }

    /**
     * Nastaví text, ktorý sa zobrazí v tomto stave (UI).
     *
     * @param newText nový text
     */
    public void setText(String newText) {
        this.textBlock.changeText(newText);
    }

    /**
     * Skryje tento status (UI element).
     */
    public void hide() {
        this.textBlock.makeInvisible();
    }

    /**
     * Zobrazí tento status (UI element).
     */
    public void show() {
        this.textBlock.makeVisible();
    }
}
