package ui.status;

import fri.shapesge.FontStyle;
import fri.shapesge.TextBlock;
import utils.Vector2D;

public abstract class Status {
    private final TextBlock textBlock;
    private final Vector2D position;

    public Status(Vector2D position, String label) {
        this.position = position;
        this.textBlock = new TextBlock(label, this.position.getX(), this.position.getY());
        this.textBlock.changeFont("Arial", FontStyle.BOLD, 24);
        this.textBlock.makeVisible();
    }

    public void setText(String newText) {
        this.textBlock.changeText(newText);
    }

    public void hide() {
        this.textBlock.makeInvisible();
    }

    public void show() {
        this.textBlock.makeVisible();
    }
}
