package controller.gameEngine;

import controller.gameSwitcher.SceneController;
import javafx.stage.Stage;

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
	 * 
	 */
	Stage getStage();

	SceneController getSceneController();

	String getPlayerName();

    void stop();
}
