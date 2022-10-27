package model;

import com.almasb.fxgl.core.math.Vec2;

import java.util.List;

abstract class GunImpl implements Gun {
	private int degRange;
	private Ship actualShip;

	public GunImpl(int degRange, Ship ship) {
		this.degRange = degRange;
		this.actualShip = ship;
	}

	public Bullet shot(Vec2 direction) {
		// TODO Auto-generated method stub
		return BulletFactory.BasicBullet(actualShip.getPosition(), actualShip.getDirection());

	}

	public boolean isInRange(Vec2 shipPos, Vec2 direction, List<Ship> enemy) {
		// TODO Auto-generated method stub
	return enemy.stream()
				.anyMatch(e -> {
					final Vec2 enemyDir = shipPos.copy().sub(e.getPosition()).normalizeLocal();
					return Math.abs(Math.acos(Vec2.dot(enemyDir, direction))) < this.degRange / 2 
							&& enemyDir.angle() * e.getPosition().angle() > 0;
				});			

	}

	public float getDegRange() {
		// TODO Auto-generated method stub
		return this.degRange;
	}

}

public class GunFactory {

	public static Gun rifle(Ship spaceship) {
		// TODO Auto-generated method stub
		class rifle extends GunImpl {

			public rifle(int degRange, Ship ship) {
				super(degRange, ship);
				// TODO Auto-generated constructor stub
			}

		}
		;
		return new rifle(10, spaceship);
	}

	public static Gun missile(Ship spaceship) {
		// TODO Auto-generated method stub
		class missile extends GunImpl {

			public missile(int degRange, Ship ship) {
				super(degRange, ship);
				// TODO Auto-generated constructor stub
			}

		}
		return new missile(45, spaceship);

	}

	public static Gun shootgun(Ship spaceship) {
		// TODO Auto-generated method stub
		class shootgun extends GunImpl {

			public shootgun(int degRange, Ship ship) {
				super(degRange, ship);
				// TODO Auto-generated constructor stub
			}

		}
		return new shootgun(30, spaceship);
	}

	/**
	 * creates and returns the player gun to the ship
	 * @param ship the player
	 * @param damage how much damage the bullet does, has a default value
	 * @param maxSpeed how fast the bullet can traverse the map
	 * @param acceleration how fast the bullet can reach its top speed
	 * @param rotationSpeed if the bullet can curve
	 * @return gun set with the default values
	 */
	public static Gun playerGun(PlayerShip ship, int damage, float maxSpeed, float acceleration, float rotationSpeed) {
		/**
		 * PlayerGun implementation
		 */
		class PlayerGun extends GunImpl {
			/**
			 * constructor
			 * @param degRange range of the bullets
			 * @param playerShip the player to give it to
			 * @param bulletDamage how much damage the bullet does
			 * @param bulletMaxSpeed top speed of the bullet
			 * @param bulletAcceleration how fast the bullet can get to its top speed
			 * @param bulletRotationSpeed if the bullet can curve
			 */
			public PlayerGun(int degRange, PlayerShip playerShip, int bulletDamage, float bulletMaxSpeed, float bulletAcceleration, float bulletRotationSpeed) {
				super(degRange, playerShip);
				this.bulletDamage = bulletDamage;
				this.bulletMaxSpeed = bulletMaxSpeed;
				this.bulletAcceleration = bulletAcceleration;
				this.bulletRotationSpeed = bulletRotationSpeed;
			}

			//Use predefined values from PlayerShipValues
			private int bulletDamage;
			private float bulletMaxSpeed;
			private float bulletAcceleration;
			private float bulletRotationSpeed;

			public Bullet shot() {
				return BulletFactory.playerBullet(bulletMaxSpeed, bulletAcceleration, bulletRotationSpeed, bulletDamage,
						ship.getPosition(), ship.getDirection());
			}
		}
		return new PlayerGun(40, ship, damage, maxSpeed, acceleration, rotationSpeed);
	}

}
