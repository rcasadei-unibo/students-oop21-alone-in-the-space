package controller.eventController;

import controller.collisionDetection.Collision;
import controller.gameSwitcher.SceneController;
import model.hud.HUDPowerUp;
import view.hud.HUDImpl;

import java.io.IOException;

public interface EventController {

    /**
     * @return collision.
     */
    Collision getCollision();

    /**
     * @return the value of the points.
     */
    int checkPoints();

    /**
     * @return the remaining life points.
     */
    int checkLifePoints();

    HUDImpl getHudBuilder();

    /**
     *
     * @param sceneController
     * @throws IOException
     */
    void endGame(SceneController sceneController) throws IOException;

    /**
     * @return the game status.
     */
    boolean checkGameStatus();

    /**
     * @return powerUp HUD reference.
     */
    HUDPowerUp getPowerUp();
}
