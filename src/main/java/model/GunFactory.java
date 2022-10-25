package model;

import java.util.List;

import com.almasb.fxgl.core.math.Vec2;

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

	public Boolean isInRange(Vec2 shipPos, Vec2 direction, List<Vec2> enemyPos) {
		// TODO Auto-generated method stub
	return enemyPos.stream()
				.anyMatch(e -> {
					Vec2 enemyDir=shipPos.copy().sub(e).normalizeLocal();
					return Math.abs(Math.acos(Vec2.dot(enemyDir, direction))) < this.degRange/2 
							&& enemyDir.angle()*e.angle()>0;
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

	public static Gun playerGun(PlayerShip ship, float damage, float maxSpeed, float acceleration, float rotationSpeed) {

		class PlayerGun extends GunImpl {
			public PlayerGun(int degRange, PlayerShip playerShip) {
				super(degRange, playerShip);
			}


			public PlayerGun(int degRange, PlayerShip playerShip, float bulletDamage,float bulletMaxSpeed, float bulletAcceleration, float bulletRotationSpeed) {
				super(degRange, playerShip);
				this.bulletDamage = bulletDamage;
				this.bulletMaxSpeed = bulletMaxSpeed;
				this.bulletAcceleration = bulletAcceleration;
				this.bulletRotationSpeed = bulletRotationSpeed;
			}

			//Use predefined values from PlayerShipValues
			private float bulletDamage;
			private float bulletMaxSpeed;
			private float bulletAcceleration;
			private float bulletRotationSpeed;

			public void changeDamage(float damage) {
				this.bulletDamage = damage;
			}

			public Bullet shot() {
				return BulletFactory.playerBullet(bulletMaxSpeed, bulletAcceleration, bulletRotationSpeed, bulletDamage,
						ship.getPosition(), ship.getDirection());
			}
		}

		return new PlayerGun(40, ship, damage, maxSpeed, acceleration, rotationSpeed);
	}

}
