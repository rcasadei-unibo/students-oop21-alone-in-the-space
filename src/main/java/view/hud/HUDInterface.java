package view.hud;

import controller.collisionDetection.Collision;
import model.hud.HUDLife;
import model.hud.HUDPoints;
import model.hud.HUDPowerUp;

public interface HUDInterface {

    boolean checkGameStatus();

    Collision getCollision();

    int checkPoints();

    int checkLives();

    HUDPowerUp getPowerUpImpl();

    HUDLife getLifeImpl();

    HUDPoints getPointsImpl();
}
