package controller.gameController;

import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.inputController.InputController;
import controller.playerController.PlayerShipController;
import controller.sceneManager.SceneManager;
import model.Ship;
import utilities.InputCommands;
import view.GameMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GameControllerImpl implements GameController {

	private GameMap gameMap;
	private SceneManager sceneManager;
	private EventController eventController;
	private PlayerShipController playerShipController;
	private InputController inputController;
	private Collection<Ship> enemies;

	public GameControllerImpl(final GameMap gameMap) {
		this.gameMap = gameMap;
		this.gameMap.setBackgroundImage(/* todo */);

		this.playerShipController = new PlayerShipController();
		// TODO playerShipController
		this.gameMap.setPlayer(this.playerShipController);

		this.sceneManager = new SceneManager(this.gameMap);
		this.eventController = new EventControllerImpl(this.gameMap);
		this.enemies=this.gameMap.getActiveEnemyShips();
		
	}

	@Override
	public void update(final long deltaTime) {
		this.inputController.updatePlayerTasks();
		// TODO player movements.
		if (this.inputController.isTaskActive(InputCommands.UP)) {
			// this.player.setDirection(Direction.UP);
		}
		if (this.inputController.isTaskActive(InputCommands.DOWN)) {
			// this.player.setDirection(Direction.DOWN);
		}
		if (this.inputController.isTaskActive(InputCommands.LEFT)) {
			// this.player.setDirection(Direction.LEFT);
		}
		if (this.inputController.isTaskActive(InputCommands.RIGHT)) {
			// this.player.setDirection(Direction.RIGHT);
		}

		if (this.inputController.isTaskActive(InputCommands.ATTACK)) {
			// this.player.setDirection(Direction.ATTACK);
		}

		if (this.inputController.isTaskActive(InputCommands.POWER_UP)) {
			// this.player.setDirection(Direction.POWER_UP);
		}

		
		
		this.eventController.getCollision().checkAllCollision(this.playerShipController.getPlayerShip(),
				this.enemies, this.gameMap.getBulletsShotByPlayer(),
				this.gameMap.getBulletsShotByPlayer());
		//this.playerShipController.update(deltaTime);

		this.enemies.forEach((Ship enemy)->{
			if(enemy.isInRangeOfAttack(List.of(this.playerShipController.getPlayerShip()), deltaTime)) {
				this.gameMap.addEnemyBullet(enemy.shot());
			}
		});
		
		
		this.sceneManager.update(deltaTime);
		if (!this.eventController.checkGameStatus()) {
			this.eventController.endGame(this.gameMap.getGameEngine().getSceneController());
			this.gameMap.getGameEngine().stop();
			try {
				this.gameMap.getGameEngine().getSceneController().getRanking
						.addPlayer(this.gameMap.getGameEngine().getPlayerName(), this.eventController.checkPoints());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public GameMap getGameMap() {
		return this.gameMap;
	}

	public final void setInputController(final InputController inputController) {
		this.inputController = inputController;
		this.inputController.changeScene(this.playerShipController.display().getScene());
	}
}
