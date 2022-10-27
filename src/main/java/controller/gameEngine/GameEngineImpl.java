package controller.gameEngine;

import com.almasb.fxgl.core.math.Vec2;
import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.gameController.GameControllerImpl;
import controller.gameSwitcher.SceneController;
import javafx.stage.Stage;
import model.EnemyFactory;
import model.Ship;
import utilities.EnumInt;
import view.GameMapImpl;
import view.WindowManager;
import view.WindowManagerImpl;

public class GameEngineImpl implements GameEngine {

	private static final long PERIOD = 100L;
	
	private GameControllerImpl game;
	private long enemyTimer;
	private static final long DELTAENEMY = 5000;
	private double difficultFactor=1;
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

		while (event.checkGameStatus()) {
			long current = System.currentTimeMillis();
			long elapsed = (current - lastTime);

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
	public void update(long elapsed) {
		// TODO Auto-generated method stub
		this.game.update(elapsed);
		long current = System.currentTimeMillis();
		if ((current - this.enemyTimer) > this.DELTAENEMY/this.difficultFactor) {
			this.gameMap.addEnemyShip(randomShip());
			this.enemyTimer = current;
			this.difficultFactor*=1.02;
		}
	}

	private Ship randomShip() {
		// TODO Auto-generated method stub
		int typeShip = (int) (Math.random() * 3) + 1;
		Vec2 spawnPosition = new Vec2((float) this.gameMap.getWidth()/2, 0);
		spawnPosition.setFromAngle(Math.random() * 360);
		spawnPosition.addLocal((float) this.gameMap.getWidth()/2,( float) this.gameMap.getHeight()/2);
		switch (typeShip) {
		case 1:
			return EnemyFactory.BasicEnemy(spawnPosition);
		case 2:
			return EnemyFactory.MissileEnemy(spawnPosition);
		case 3:
			return EnemyFactory.RifleEnemy(spawnPosition);
		}
		return null;
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initGame() {
		this.game = new GameControllerImpl(this.gameMap);
		this.event = new EventControllerImpl(this.gameMap);
		this.enemyTimer = System.currentTimeMillis();
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

	@Override
	public void stop() {

	}

	public SceneController getSceneController() {
		return this.sceneController;
	}

}
