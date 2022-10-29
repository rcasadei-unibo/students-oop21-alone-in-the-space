package controller.gameController;

import com.almasb.fxgl.core.math.Vec2;
import controller.eventController.EventController;
import controller.eventController.EventControllerImpl;
import controller.inputController.InputController;
import controller.playerController.PlayerShipController;
import controller.playerController.PlayerShipControllerImpl;
import controller.sceneManager.SceneManager;
import javafx.scene.image.Image;
import model.bullet.Bullet;
import model.ship.Ship;
import model.status.StatusImpl;
import utilities.EnumInt;
import utilities.EnumString;
import utilities.InputCommands;
import utilities.PlayerValues;
import view.GameMap;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GameControllerImpl implements GameController {

    private static final long DELTAENEMY = 5_000_000;
    private double difficultFactor = 1;
    private GameMap gameMap;
    private SceneManager sceneManager;
    private EventController eventController;
    private PlayerShipController playerShipController;
    private InputController inputController;
    private Collection<Ship> enemies;
    private long enemyTimer;


    public GameControllerImpl(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.gameMap.setBackgroundImage(EnumString.IMAGE_FOLDER.getValue() + "skybox13.jpg");
        this.playerShipController = new PlayerShipControllerImpl(new Vec2(100, 100), new Image("images/shipPlayer.png"));
        this.gameMap.setPlayer(this.playerShipController.getPlayerShip());
		this.gameMap.setStatus(new StatusImpl(0, PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"), EnumInt.LIVES.getValue()));
		this.playerShipController.setStatus(this.gameMap.getStatus());
        this.sceneManager = new SceneManager(this.gameMap);
        this.eventController = new EventControllerImpl(this.gameMap);
        this.enemies = this.gameMap.getActiveEnemyShips();
        this.enemyTimer = System.currentTimeMillis();
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
			final Optional<Bullet> playerBulletShot = Optional.ofNullable(this.playerShipController.shot());
			playerBulletShot.ifPresent(bullet -> this.gameMap.addPlayerBullet(bullet));

		}

		if (this.inputController.isTaskActive(InputCommands.POWER_UP)) {
			this.playerShipController.activatePowerUp();
		}

		if (!this.inputController.isTaskActive(InputCommands.UP) && !this.inputController.isTaskActive(InputCommands.DOWN)) {
			this.playerShipController.thrustReleased();
		}


		this.eventController.getCollision().checkAllCollision(this.playerShipController.getPlayerShip(),
				this.enemies, this.gameMap.getBulletsShotByPlayer(),
				this.gameMap.getBulletsShotByEnemies());
		this.playerShipController.update(deltaTime);
	        this.gameMap.removeDeadEntity();
		this.enemies.forEach((Ship enemy) -> {
			if (enemy.isInRangeOfAttack( deltaTime)) {
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
