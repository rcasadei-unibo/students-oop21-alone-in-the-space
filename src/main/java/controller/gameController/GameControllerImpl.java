package controller.gameController;

import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.inputController.InputController;
import controller.playerController.PlayerShipController;
import controller.playerController.PlayerShipControllerImpl;
import controller.sceneManager.SceneManager;
import javafx.scene.image.Image;
import model.Bullet;
import model.Ship;
import utilities.EnumString;
import utilities.InputCommands;
import view.GameMap;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.almasb.fxgl.core.math.Vec2;

public class GameControllerImpl implements GameController {

	private GameMap gameMap;
	private SceneManager sceneManager;
	private EventController eventController;
	private PlayerShipController playerShipController;
	private InputController inputController;
	private Collection<Ship> enemies;


    public GameControllerImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.gameMap.setBackgroundImage(EnumString.IMAGE_FOLDER.getValue() + "skybox13.jpg");


		this.playerShipController = new PlayerShipControllerImpl(new Vec2(100,100), new Image("images/ship_0.png"));
		// TODO playerShipController
		this.gameMap.setPlayer(this.playerShipController.getPlayerShip());

		this.sceneManager = new SceneManager(this.gameMap);
		this.eventController = new EventControllerImpl(this.gameMap);
		this.enemies = this.gameMap.getActiveEnemyShips();
		
	}

	@Override
	public void update(final long deltaTime) {
		this.inputController.updatePlayerTasks();
		// TODO player movements.
		if (this.inputController.isTaskActive(InputCommands.UP)) {
			this.playerShipController.thrust(InputCommands.UP);
		}
		if (this.inputController.isTaskActive(InputCommands.DOWN)) {
			this.playerShipController.thrust(InputCommands.DOWN);
		}
		if (this.inputController.isTaskActive(InputCommands.LEFT)) {
			this.playerShipController.rotate(InputCommands.LEFT);
		}
		if (this.inputController.isTaskActive(InputCommands.RIGHT)) {
			this.playerShipController.rotate(InputCommands.RIGHT);
		}

		if (this.inputController.isTaskActive(InputCommands.ATTACK)) {
			Optional<Bullet> playerBulletShot = Optional.of(this.playerShipController.shot());
			if (!playerBulletShot.isEmpty()) {
				this.gameMap.addPlayerBullet(playerBulletShot.get());
			}

		}

		if (this.inputController.isTaskActive(InputCommands.POWER_UP)) {
			this.playerShipController.activatePowerUp();
		}

		if (!this.inputController.isTaskActive(InputCommands.UP) && !this.inputController.isTaskActive(InputCommands.DOWN)) {
			this.playerShipController.thrustReleased();
		}

		
		
		this.eventController.getCollision().checkAllCollision(this.playerShipController.getPlayerShip(),
				this.enemies, this.gameMap.getBulletsShotByPlayer(),
				this.gameMap.getBulletsShotByPlayer());
		//this.playerShipController.update(deltaTime);

		this.enemies.forEach((Ship enemy) -> {
			if (enemy.isInRangeOfAttack(List.of(this.playerShipController.getPlayerShip()), deltaTime)) {
				this.gameMap.addEnemyBullet(enemy.shot());
			}
		});
		
		this.sceneManager.update(deltaTime);
		//this.playerShipController.update(deltaTime);should be included in the scene manager
		
		if (!this.eventController.checkGameStatus()) {
			this.gameMap.getGameEngine().stop();
			try {
				this.eventController.endGame(this.gameMap.getGameEngine().getSceneController());
				this.gameMap.getGameEngine().getSceneController().getRanking()
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
