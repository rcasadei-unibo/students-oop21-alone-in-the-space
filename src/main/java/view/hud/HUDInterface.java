package view.hud;

import controller.collisionDetection.Collision;

public interface HUDInterface {

    boolean checkGameStatus();

    Collision getCollision();

    int checkPoints();

    int checkLives();
}
