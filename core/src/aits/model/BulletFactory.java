package aits.model;

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
}
