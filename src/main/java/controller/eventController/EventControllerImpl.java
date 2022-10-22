package controller.eventController;

import java.io.IOException;

import controller.gameEngine.GameEngine;

public class EventControllerImpl implements EventController{
    @Override
    public int checkPoints() {
        return 0;
    }

    @Override
    public int checkLifePoints() {
        return 0;
    }

    @Override
    public void endGame(GameEngine gameEngine) throws IOException {

    }

    @Override
    public boolean checkGameStatus() {
        return false;
    }
}
