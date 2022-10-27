package controller.gameEngine;

import controller.gameSwitcher.SceneController;
import javafx.stage.Stage;
import view.GameMap;

/**
 * 
 * This interface handle the loop of the game.
 *
 */
public interface GameEngine {

    /**
     * Capture frames of view once every "Period".
     */
    void mainLoop();

    /**
     * Detect the action executed by the mouse or the keyboard.
     */

    void processInput();
    /**
     * Update model state following input commands.
     * @param elapsed time
     */

    void update(long elapsed);
    /**
     * Update view interface.
     */

    void render();

    /**
     * Initialize the launch of game.
     */
    void initGame();

    /**
     * Give the actual stage.
     * @return a Stage
     */
    Stage getStage();

    /**
     * Give the actual scene controller.
     * @return a SceneController
     */
    SceneController getSceneController();

    /**
     * Give the name player.
     * @return a String
     */
    String getPlayerName();

    /**
     * Set the name player.
     * @param name
     */
    void setPlayerName(String name);

    /**
     * Give the game map.
     * @return a GameMap
     */
    GameMap getGameMap();

    /**
     * Stop the game session.
     */
    void stop();
}
