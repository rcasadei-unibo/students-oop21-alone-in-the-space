package model;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractBullet implements Bullet {
	private boolean alive;
	protected float maxSpeed;
	protected float acceleration;
	protected float rotationSpeed;
	private int damage;
	protected Vec2 speed;
	protected Vec2 direction;
	protected Vec2 position;
	private ImageView sprite;
	

	public AbstractBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position, Vec2 direction) {

		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.damage = damage;
		this.position = position;
		this.direction = direction;
		this.alive = true;
		this.speed=direction.normalize().mul(maxSpeed);
	}
	
	public void move(long deltaTime) {
		deltaTime /= 1000; //expected delta time to be in millisecond
		this.speed = this.speed.add(direction.copy().mul(deltaTime));
		if (this.speed.length() > maxSpeed) {
			this.speed = this.speed.copy().normalize().mul(this.maxSpeed);
		}
		this.position = this.position.add(speed.copy().mul(deltaTime));
	}
	

	public Boolean isCollided() {
		return !this.alive;		
	};

	public void destroy() {
		this.alive = false;
	};

	public int getDamage() {
		return this.damage;
	};


	@Override
	public Vec2 getPosition() {
		// TODO Auto-generated method stub
		return this.position.copy();
	}

	@Override
	public Boolean isAlive() {
		// TODO Auto-generated method stub
		return this.alive;
	}
	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return this.sprite;
	}

	@Override
	public Vec2 getDirection() {
		// TODO Auto-generated method stub
		return this.direction.copy();
	}
	@Override
	public void setPosition(Vec2 newpos) {
		// TODO Auto-generated method stub
		 this.position = newpos;
	}
	// TODO Auto-generated method stub
		public void setSprite(Image img) {
			this.sprite = new ImageView();
			this.sprite.setImage(img);
		}
}
