package ui.pickers;

import core.Game;
import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.Vector2D;

public class MonkeyPicker {
    private final Image image;
    private Vector2D position;
    private Manager manager;
    private int cost;
    private String name;

    /**
     * Vytvorí tlačidlo (picker) pre určitý typ opice na obrazovke.
     *
     * @param name názov opice (používaný pri vytváraní obrazu a pri výbere)
     * @param cost cena opice
     * @param position pozícia pickeru na obrazovke
     */
    public MonkeyPicker(String name, int cost, Vector2D position) {
        this.position = position;
        this.cost = cost;
        this.name = name;

        this.image = new Image("res/assets/monkeys/" + name + "UI.png");
        this.image.changePosition(position.getX(), position.getY());
        this.image.makeVisible();

        this.manager = new Manager();
        this.manager.manageObject(this);
    }
    /**
     * Reaguje na kliknutie používateľa; ak je kliknutie v oblasti pickeru, nastaví
     * v hre aktuálne vybranú opicu.
     *
     * @param x X súradnica kliknutia
     * @param y Y súradnica kliknutia
     */
    public void click(int x, int y) {
        if (x >= this.position.getX() && x <= this.position.getX() + 64 && y >= this.position.getY() && y <= this.position.getY() + 64) {
            Game.setCurrentlySelectedMonkey(this.name);
        }
    }

}
