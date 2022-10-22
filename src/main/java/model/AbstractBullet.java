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
	private float damage;
	protected Vec2 speed;
	protected Vec2 direction;
	protected Vec2 position;
	private ImageView sprite;
	
	public AbstractBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vec2 position) {
		super();
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.damage = damage;
		this.position = position;
		this.alive= true;
	}
	
	public void move(float deltaTime) {
		this.speed = this.speed.mulAdd(direction.cpy(), deltaTime);
		if (this.speed.len() > maxSpeed) {
			this.speed=this.speed.cpy().nor().scl(this.maxSpeed );
		}
		this.position = this.position.mulAdd(this.speed, deltaTime);
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


	@Override
	public Vec2 getPosition() {
		// TODO Auto-generated method stub
		return this.position;
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
		return this.direction.cpy();
	}
	@Override
	public void setPosition(Vec2 newpos) {
		// TODO Auto-generated method stub
		 this.position=newpos;
		 return ;
	}
	// TODO Auto-generated method stub
		public void setSprite(Image img) {
			this.sprite=new ImageView();
			this.sprite.setImage(img);

		}
}
