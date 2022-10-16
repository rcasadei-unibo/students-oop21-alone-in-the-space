package controller.gameController;

import controller.inputController.InputController;
import controller.sceneManager.SceneManager;
import model.PlayerShip;
import utilities.InputCommands;
import view.GameMap;

public class GameControllerImpl implements GameController {

    private GameMap gameMap;
    private SceneManager frame;
    private GameEventController gameEventController;
    private PlayerShip player;
    private InputController inputController;
    //status controller
    //enemy AI

    public GameControllerImpl(final GameMap gameMap) {
        this.gameMap  = gameMap;
        this.gameMap.setBackgroundImage("TODO");

        /* player info */
        this.player = new PlayerShip();
        //TODO this.player.setPosition(position);
        this.gameMap.setPlayer(this.player);

        this.frame = new SceneManager(this.gameMap);
        this.gameEventController = new GameEventController(this.gameMap);
        //TODO aicontroller
        //TODO statuscontroller
    }

    @Override
    public void update() {
        this.inputController.updatePlayerTasks();


        //TODO da finire
    }

    /**
     *
     * @return gameMap.
     */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    public final void setInputController(final InputController inputController) {
        this.inputController = inputController;
        this.inputController.changeScene(this.player.getNode().getScene());
    }
}
