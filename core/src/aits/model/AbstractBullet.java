package aits.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class AbstractBullet implements Bullet {
	private float maxSpeed;
	private float acceleration;
	private float rotationSpeed;
	private float damage;
	private Vector2 speed;
	private Vector2 direction;
	private Vector2 position;
	private Texture texture;
	
	public AbstractBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position,
			Texture texture) {
		super();
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.damage = damage;
		this.position = position;
		this.texture = texture;
	}

	public void move(float deltaTime) {
		this.speed = this.speed.mulAdd(direction.cpy(), deltaTime);
		if(this.speed.len2()>maxSpeed) {
			this.speed.cpy().scl(this.maxSpeed/this.speed.len());
		}
		this.position = this.position.add(this.speed);
	};

	public Boolean isCollided() {
		return null;
		
	};

	public void destroy() {

	};

	public float getDamage() {
		return this.getDamage();
	};
	
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return this.texture;
	}
}
