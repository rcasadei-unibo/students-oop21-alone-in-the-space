package controller.gameEngine;

import com.almasb.fxgl.core.math.Vec2;

import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.gameController.GameController;
import controller.gameController.GameControllerImpl;
/*import controller.gameController.GameController;
import controller.gameController.GameControllerImpl;*/
import javafx.stage.Stage;
import model.EnemyFactory;
import model.Ship;
import utilities.EnumInt;
import view.GameMap;
import view.GameMapImpl;

public class GameEngineImpl implements GameEngine {

	private static final long PERIOD = 100L;
	private long enemyTimer;
	private static final long DELTAENEMY = 5000;
	private double difficultFactor=1;
	private GameController game;
	private GameMap map;
	private EventController event;
	private Stage stage;

	public GameEngineImpl() {

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
			this.map.addEnemyShip(randomShip());
			this.enemyTimer = current;
			this.difficultFactor*=1.02;
		}
	}

	private Ship randomShip() {
		// TODO Auto-generated method stub
		int typeShip = (int) (Math.random() * 3) + 1;
		Vec2 spawnPosition = new Vec2((float) this.map.getWidth(), 0);
		spawnPosition.setFromAngle(Math.random() * 360);
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
		this.map = new GameMapImpl(EnumInt.HEIGHT.getValue(), EnumInt.WIDTH.getValue(), this);
		this.game = new GameControllerImpl(this.map);
		this.event = new EventControllerImpl(this.map);
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

}
