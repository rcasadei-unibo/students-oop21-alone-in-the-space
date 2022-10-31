package controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.almasb.fxgl.core.math.Vec2;

import controller.collisionDetection.Collision;
import controller.collisionDetection.CollisionImpl;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import model.bullet.Bullet;
import model.bullet.BulletFactory;
import model.ship.EnemyFactory;
import model.ship.PlayerShip;
import model.ship.Ship;
import utilities.EnumInt;
import view.GameMap;
import view.GameMapImpl;

public class CollisionTest {

	private PlayerShip playerShip;

	private Ship firstEnemy;
	private Ship secondEnemy;
	private Ship thirdEnemy;
	private GameMap map;
	private Bullet basicBullet;
	private Bullet bolt;
	private Collision objCollision;

	@BeforeEach
	public void setUp() {
		new JFXPanel();
		firstEnemy = EnemyFactory.basicEnemy(new Vec2(100, 200));
		secondEnemy = EnemyFactory.rifleEnemy(new Vec2(800, 400));
		thirdEnemy = EnemyFactory.missileEnemy(new Vec2(490, 890));
		playerShip = new PlayerShip(new Vec2(500, 900), 100, 100, 100);
		playerShip.setSprite(new Image("images/shipPlayer.png"));
		basicBullet = BulletFactory.BasicBullet(new Vec2(487, 884), new Vec2(487, 884));
		bolt = BulletFactory.bolt(new Vec2(289, 135), new Vec2(289, 135));
		objCollision = new CollisionImpl(map, null);
		map = new GameMapImpl(EnumInt.WIDTH.getValue(), EnumInt.HEIGHT.getValue());
	}

	@Test
	public void checkCollisionBetweenPlayerAndEnemies() {
		playerShip.getNode().setLayoutX(500);
		playerShip.getNode().setLayoutY(900);
		firstEnemy.getNode().setLayoutX(100);
		firstEnemy.getNode().setLayoutY(200);
		secondEnemy.getNode().setLayoutX(800);
		secondEnemy.getNode().setLayoutY(400);
		thirdEnemy.getNode().setLayoutX(490);
		thirdEnemy.getNode().setLayoutY(890);

		Assertions.assertFalse(objCollision.checkEnemyCollision(playerShip, secondEnemy));
		Assertions.assertTrue(objCollision.checkEnemyCollision(playerShip, thirdEnemy));
		Assertions.assertFalse(objCollision.checkEnemyCollision(playerShip, firstEnemy));
	}

	public void checkCollisionBetweenPlayerAndBullets() {
		playerShip.getNode().setLayoutX(500);
		playerShip.getNode().setLayoutY(900);
		basicBullet.getNode().setLayoutX(487);
		basicBullet.getNode().setLayoutY(884);
		bolt.getNode().setLayoutX(289);
		bolt.getNode().setLayoutY(135);

		Assertions.assertTrue(objCollision.checkBulletCollision(playerShip, basicBullet));
		Assertions.assertFalse(objCollision.checkBulletCollision(playerShip, bolt));

	}

}
