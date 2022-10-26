package controller.eventController;

import controller.collisionDetection.Collision;
import controller.gameSwitcher.SceneController;
import model.hud.HUDPowerUp;

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

    /**
     * //todo
     *
     * @param gameEngine
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
