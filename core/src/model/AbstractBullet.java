package model;

import com.badlogic.gdx.math.Vector2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractBullet implements Bullet {
	private boolean alive;
	protected float maxSpeed;
	protected float acceleration;
	protected float rotationSpeed;
	private float damage;
	protected Vector2 speed;
	protected Vector2 direction;
	protected Vector2 position;
	private ImageView sprite;
	
	public AbstractBullet(float maxSpeed, float acceleration, float rotationSpeed, float damage, Vector2 position) {
		super();
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.damage = damage;
		this.position = position;
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
	public Node getNode() {
		// TODO Auto-generated method stub
		return this.sprite;
	}

	@Override
	public Vector2 getDirection() {
		// TODO Auto-generated method stub
		return this.direction.cpy();
	}
	@Override
	public void setPosition(Vector2 newpos) {
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
