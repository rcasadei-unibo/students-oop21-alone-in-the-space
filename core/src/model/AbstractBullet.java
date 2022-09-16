package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractBullet implements Bullet {
	private boolean alive;
	protected float maxSpeed;
	protected float acceleration;
	protected float rotationSpeed;
	private float damage;
	protected Vector2 speed;
	protected Vector2 direction;
	protected Vector2 position;
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
		this.alive= true;
	}

	

	public Boolean isCollided() {
		return !this.alive;
		
	};

	public void destroy() {
		this.alive=false;
	};

	public float getDamage() {
		return this.damage;
	};
	
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return this.texture;
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public Boolean isAlive() {
		// TODO Auto-generated method stub
		return this.alive;
	}

	@Override
	public Vector2 getDirection() {
		// TODO Auto-generated method stub
		return this.direction.cpy();
	}
}
