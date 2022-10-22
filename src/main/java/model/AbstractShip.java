package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.core.math.Vec2;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractShip implements Ship {
	private float health;
	private float maxSpeed;
	private float acceleration;
	private float rotationSpeed;// need to be in radiant
	private Vec2 speed;
	private Vec2 direction;
	private Vec2 position;
	private Ship target;
	private ImageView sprite;
	private Gun gun;

	public AbstractShip(float health, float maxSpeed, float acceleration, float rotationSpeed,Vec2 newPosition ) {
		super();
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.position=newPosition;
	}

	public void move(float deltaTime) {
		float delta = calculateDir();
		double angle = Math.acos(delta);
		if (angle < gun.getDegRange()) {
			this.direction = this.direction.rotateDeg(rotationSpeed * deltaTime * (angle > 180 ? 1 : -1));
		}
		this.speed = this.speed.mulAdd(direction.cpy(), deltaTime * acceleration);
		if (this.speed.len() > maxSpeed) {
			this.speed=this.speed.cpy().nor().scl(this.maxSpeed );
		}
		this.position = this.position.mulAdd(this.speed, deltaTime );
		this.sprite.setX(this.position.x);
		this.sprite.setY(this.position.y);
	}

	public Bullet shot() {
		Bullet bullet =  gun.shot(this.direction);
		bullet.setPosition(this.position);
		return bullet;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.health = 0;
		this.target=null;
	}

	@Override
	public void setTarget(Ship target) {
		// TODO Auto-generated method stub
		this.target = target;
	}

	@Override
	public Vec2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public Vec2 getDirection() {
		// TODO Auto-generated method stub
		return this.direction;
	}
	@Override
	public void setPosition(Vec2 newpos) {
		// TODO Auto-generated method stub
		 this.position=newpos;
		 return ;
	}
	
	/**
	 * calculate the angle from the actual direction, and ideal direction 
	 * @return
	 */
	private float calculateDir() {
		Vec2 tragetDir = this.target.getPosition().cpy().sub(this.position).nor();
		return this.direction.cpy().nor().dot(tragetDir);
	}

	
	@Override
	public Boolean isInRangeOfAttack(List<Vec2> enemy) throws NullPointerException {
		// TODO Auto-generated method stub
		return gun.isInRange(this.position.cpy(), this.direction.cpy(), enemy);
	}

	@Override
	public void setGun(Gun gun) {
		// TODO Auto-generated method stub
		this.gun = gun;
	}

	// TODO Auto-generated method stub
	public void setSprite(Image img) {
		this.sprite=new ImageView();
		this.sprite.setImage(img);

	}
	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return this.sprite;
	}
	@Override
	public String drop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hit(float damage) {
		// TODO Auto-generated method stub
		this.health -= damage;
	}

	@Override
	public Boolean isAlive() {
		// TODO Auto-generated method stub
		if (this.health > 0) {
			return true;
		}
		this.destroy();
		return false;
	}

	
}
