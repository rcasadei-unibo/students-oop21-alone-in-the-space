package model;

import com.almasb.fxgl.core.math.Vec2;


import javafx.scene.image.Image;

public class BulletFactory {

	private static final String assetFolder = "";

	public static Bullet BasicBullet(Vec2 position, Vec2 direction) {

		class BasicBullet extends AbstractBullet {

			public BasicBullet(float maxSpeed, final float acceleration, float rotationSpeed, int damage, Vec2 position,
					Vec2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
				// TODO Auto-generated constructor stub
			}

		

		}
		;
		final Bullet var = new BasicBullet(0, 0, 0, 0, position, direction);
		var.setSprite(loadImage("bullet_01.png"));
		return var;
	}

	public static Bullet RifleBullet(Vec2 position, Vec2 direction) {

		class RifleBullet extends AbstractBullet {

			public RifleBullet(float maxSpeed, final float acceleration, float rotationSpeed, int damage, Vec2 position,
					Vec2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
				// TODO Auto-generated constructor stub
			}

		}
		;
		final Bullet var = new RifleBullet(0, 0, 0, 0, position, direction);
		var.setSprite(loadImage("bullet_02.png"));
		return var;

	}

	public static Bullet missile(Vec2 position, Vec2 direction, final Ship enemy) {

		class missile extends AbstractBullet {
			public missile(final float maxSpeed, final float acceleration, final float rotationSpeed, final int damage, final Vec2 position,
					Vec2 direction, Ship enemy) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
				// TODO Auto-generated constructor stub
				this.target = enemy;
			}
			private final Ship target;

			@Override
			public void move(long deltaTime) {
				// TODO Auto-generated method stub
				if (target.isAlive()) {
				} else {
					super.destroy();
				}
			}
		};
		final Bullet var = new missile(0, 0, 0, 0, position, direction, enemy);
		var.setSprite(loadImage("missile_01.png"));
		return var;

	}

	/**
	 * PlayerBullets which change value depending on the player gun modifications
	 * @param maxSpeed top speed
	 * @param acceleration how fast it reaches its top speed
	 * @param rotationSpeed if it can curve
	 * @param damage how much damage it does on impact
	 * @param position spawn position
	 * @param direction direction and trajectory to follow
	 * @return bullet entity that's been shot
	 */
	public static Bullet playerBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position, Vec2 direction) {

		class PlayerBullet extends AbstractBullet {
			public PlayerBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position, Vec2 direction) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
			}
		}
		final Bullet var = new PlayerBullet(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
		var.setSprite(loadImage("bullet_00.png"));
		return var;
	}

	private static Image loadImage(String pathName) throws IllegalArgumentException {
		Image img;

		try {
			img = new Image("" + assetFolder + pathName);
		} catch (Exception e) {
			throw e;
		}
		return img;
	}

}
