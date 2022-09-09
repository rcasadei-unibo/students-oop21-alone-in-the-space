package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class BulletFactory {
	public static Bullet BasicBullet(Vector2 position) {
		class BasicBullet extends AbstractBullet implements Bullet{

			public BasicBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position,
					Texture texture) {
				super(maxSpeed, acceleration, rotationSpeed, damage, position, texture);
				// TODO Auto-generated constructor stub
			}
			
		}
		return new BasicBullet(100, 30, 0, 10, position, null);
		 
	}
	// TODO maybe not leave static values, maybe look for a list of stats from gun
	public static Bullet PlayerBasicBullet(Vector2 position) {
		class PlayerBasicBullet extends AbstractBullet implements Bullet {
			public PlayerBasicBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position, Texture texture) {
				super(maxSpeed,acceleration,rotationSpeed,damage,position,texture);

			}
		}
		return new PlayerBasicBullet(100, 30, 0, 20, position, null);
	}
}
