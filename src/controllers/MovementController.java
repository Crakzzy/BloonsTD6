package controllers;

import balloon.Balloon;
import core.Game;
import utils.Vector2D;

import java.util.Optional;

public class MovementController {
    private static MovementController INSTANCE;

    private MovementController() {
    }

    public static MovementController getInstance() {
        if (MovementController.INSTANCE == null) {
            MovementController.INSTANCE = new MovementController();
        }
        return MovementController.INSTANCE;
    }

    public void move(Balloon balloon) {
        Optional<Vector2D> target = balloon.getTarget();

        if (target.isEmpty()) {
            return;
        }

        Vector2D currentPos = balloon.getPosition();
        double speed = balloon.getSpeed();

        int dx = target.get().getX() - currentPos.getX();
        int dy = target.get().getY() - currentPos.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= speed) {
            balloon.updatePosition(target.get());
            balloon.nextTarget();
        } else {
            int moveX = (int) Math.round((dx / distance) * speed);
            int moveY = (int) Math.round((dy / distance) * speed);

            Vector2D newPos = new Vector2D(currentPos.getX() + moveX, currentPos.getY() + moveY);
            balloon.updatePosition(newPos);
        }

        if (balloon.hasReachedEnd()) {
            Game.removeBallon(balloon);
            Game.changeHealth(Game.getHealth() - balloon.getHpToTakeOnEnd());
        }
    }
}