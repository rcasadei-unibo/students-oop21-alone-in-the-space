package controller.eventController;

import controller.gameEngine.GameEngine;
import view.GameMap;
import view.hud.HUDImpl;

import java.io.IOException;

public class EventControllerImpl implements EventController{

    //TODO
    private HUDImpl hudBuilder;

    public EventControllerImpl(final GameMap gameMap) {
        this.hudBuilder = new HUDImpl(gameMap);
    }

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
