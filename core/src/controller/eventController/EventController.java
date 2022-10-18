package controller.eventController;

import java.io.IOException;

import controller.gameEngine.GameEngine;

public interface EventController {

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
    void endGame(GameEngine gameEngine) throws IOException;

    /**
     * @return the game status.
     */
    boolean checkGameStatus();
}
