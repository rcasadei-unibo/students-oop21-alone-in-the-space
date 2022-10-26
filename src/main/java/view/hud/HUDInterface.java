package view.hud;

import controller.collisionDetection.Collision;
import model.hud.HUDPowerUp;

public interface HUDInterface {

    boolean checkGameStatus();

    Collision getCollision();

    int checkPoints();

    int checkLives();

    HUDPowerUp getPowerUpImpl();
}
