package ui;

import fri.shapesge.FontStyle;
import fri.shapesge.TextBlock;
import utils.IHideable;
import utils.Vector2D;

public abstract class Status implements IHideable {
    private TextBlock textBlock;
    private Vector2D position;
    private String label;

    public Status(Vector2D position, String label) {
        this.position = position;
        this.label = label;
        this.textBlock = new TextBlock(label, this.position.getX(), this.position.getY());
        this.textBlock.changeFont("Arial", FontStyle.BOLD, 24);
        this.textBlock.makeVisible();
    }

    public void setText(String newText) {
        this.textBlock.changeText(newText);
    }

    @Override
    public void hide() {
        this.textBlock.makeInvisible();
    }

    @Override
    public void show() {
        this.textBlock.makeVisible();
    }
}
