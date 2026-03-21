package balloon;

import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.ITickable;
import utils.Vector2D;

public abstract class Ballon implements ITickable {
    protected short speed;
    protected int hp;
    protected Vector2D position;
    protected Image image;
    protected Manager manager;

    @Override
    public void tick() {

    }

}
