package balloon;

import fri.shapesge.Image;
import fri.shapesge.Manager;
import utils.Vector2D;

public class RedBalloon extends Ballon {

    public RedBalloon(Vector2D position) {
        this.speed = 10;
        this.hp = 20;
        this.position = position;
        this.image = new Image("res/assets/balloons/red.png");
        this.image.changePosition(position.getX(), position.getY());
        this.image.makeVisible();

        this.manager = new Manager();
        this.manager.manageObject(this);
    }

}
