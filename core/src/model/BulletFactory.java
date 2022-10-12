package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class BulletFactory {

	private static final String assetFolder="";
	public static Bullet BasicBullet(Vector2 position) {

		return new AbstractBullet(100, 30, 0, 10, position) {
			
			@Override
			public void move(float deltaTime) {
				super.speed = super.speed.mulAdd(direction.cpy(), deltaTime);
				if (super.speed.len2() > maxSpeed) {
					super.speed.cpy().scl(super.maxSpeed / super.speed.len());
				}
				super.position = super.position.add(super.speed);
			}

		};
	}

	public static Bullet RifleBullet(Vector2 position, Vector2 direction) {

		return new AbstractBullet(0, 0, 0, 0, direction) {
			
			@Override
			public void move(float deltaTime) {
				super.speed = super.speed.mulAdd(direction.cpy(), deltaTime);
				if (super.speed.len2() > maxSpeed) {
					super.speed.cpy().scl(super.maxSpeed / super.speed.len());
				}
				super.position = super.position.add(super.speed);
			}

		};

	}

	public static Bullet missile(Vector2 position, final Ship enemy) {

		return new AbstractBullet(0, 0, 0, 0, null) {
			private final Ship target = enemy;

			@Override
			public void move(float deltaTime) {
				// TODO Auto-generated method stub
				if(target.isAlive()) {
					
					
					
				}else {
					super.destroy();
				}
			}
		};

	}
	private static Image loadImage(String pathName) throws IllegalArgumentException{
		Image img;
		
		try {
			img = new Image(""+assetFolder+pathName);
		} catch (Exception e) {
			throw e;
		}
		return img;
	}

}
