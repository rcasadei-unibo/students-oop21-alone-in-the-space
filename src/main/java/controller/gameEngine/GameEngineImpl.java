package controller.gameEngine;

import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.gameController.GameController;
import controller.gameController.GameControllerImpl;
import controller.gameSwitcher.SceneController;
import javafx.stage.Stage;
import utilities.EnumInt;
import view.GameMapImpl;
import view.WindowManager;
import view.WindowManagerImpl;

public class GameEngineImpl implements GameEngine {
	
	private static final long PERIOD = 100L;
	
	private GameControllerImpl game;
	private EventController event;
	private Stage stage;
	private String playerName;
	private GameMapImpl gameMap;
	private SceneController sceneController;
	private WindowManager windowManager;


	public GameEngineImpl(final SceneController sceneController) {
		this.sceneController = sceneController;
		this.windowManager = new WindowManagerImpl(this.sceneController);
		this.gameMap = new GameMapImpl(EnumInt.WIDTH.getValue(), EnumInt.HEIGHT.getValue(), this);
		this.windowManager.addGameMap(this.gameMap);

		this.game = new GameControllerImpl(this.gameMap);
		this.game.setInputController(this.sceneController.getInputController());

	}
	
	@Override
	public void mainLoop() {
		long lastTime = System.currentTimeMillis();
		
		while(event.checkGameStatus()) {
			long current = System.currentTimeMillis();
			int elapsed = (int) (current - lastTime);
			
			processInput();
			update(elapsed);
			render();
			
			try {
                waitForNextFrame(current);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            lastTime = current;
		}
	}

	@Override
	public void processInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initGame() {
		this.map = new GameMapImpl(EnumInt.HEIGHT.getValue(), EnumInt.WIDTH.getValue(), this); 
//		this.game = new GameControllerImpl(this.map);
		this.event = new EventControllerImpl();
	}
	
	protected void waitForNextFrame(final long current) {
        long dt = System.currentTimeMillis() - current;

        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (IllegalArgumentException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
	public Stage getStage() {
		return this.stage;
	}

	public void setPlayerName(final String name) {
		this.playerName = name;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public GameMapImpl getGameMap() {
		return this.gameMap;
	}

	public SceneController getSceneController() {
		return this.sceneController;
	}

}
