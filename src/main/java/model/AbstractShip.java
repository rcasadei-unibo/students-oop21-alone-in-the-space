package model;

import java.util.List;
import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractShip implements Ship {
	private float health;
	private float maxSpeed;
	private float acceleration;
	private float rotationSpeed; // need to be in radiant
	private long lastAttack = 0;
	private final long attackCooldown;
	private Vec2 speed;
	private Vec2 direction;
	private Vec2 position;
	private Ship target;
	private ImageView sprite;
	private Gun gun;

	public AbstractShip(float health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD, Vec2 newPosition) {
		super();
		this.attackCooldown = attackCD;
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.position = newPosition;
		this.direction=new Vec2(0,0);
		this.speed=new Vec2(0,0);
	}

	public void move(long deltaTime) {
		final int halfTurn = 180; //angle degree 
		final int Turn = 360;
		deltaTime /= 1000; //conversion to seconds
		float delta = calculateDir();
		double angle = Math.acos(delta);
		if (angle > gun.getDegRange()) {
			// how to rotate? TRUE->counterclockwise, FALSE->clockwise
			Boolean verseOfRotation = (Turn - this.target.getPosition().copy().sub(this.position).angle()) % Turn
					+ this.direction.angle() > halfTurn;
			this.direction = rotate(direction,
					(Turn + this.rotationSpeed * deltaTime * (verseOfRotation ? 1 : -1)) % Turn);
		}
		this.speed = this.speed.add(direction.copy().mul(deltaTime * acceleration));
		if (this.speed.length() > maxSpeed) {
			this.speed = this.speed.copy().normalize().mul(this.maxSpeed);
		}
		this.position = this.position.add(speed.copy().mul(deltaTime));
	}

	public Bullet shot() {
	    	this.lastAttack -= this.attackCooldown;
		Bullet bullet = gun.shot(this.direction.copy());
		bullet.setPosition(this.position.copy());
		return bullet;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.health = 0;
		this.target = null;
	}

	@Override
	public void setTarget(Ship target) {
		// TODO Auto-generated method stub
		this.target = target;
	}

	@Override
	public Vec2 getPosition() {
		// TODO Auto-generated method stub
		return position.copy();
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
		return;
	}

	@Override
	public Boolean isInRangeOfAttack(List<Ship> enemy, long deltaTime) throws NullPointerException {
		// TODO Auto-generated method stub
	     //gun.isInRange(this.position.copy(), this.direction.copy(), enemy);
		if (deltaTime - this.lastAttack > attackCooldown) {
		    //can attack;
		    this.lastAttack+=deltaTime;
		   // return gun.isInRange(this.position.copy(), this.direction.copy(), enemy);
		}
		return false;
	}

	@Override
	public void setGun(Gun gun) {
		// TODO Auto-generated method stub
		this.gun = gun;
	}

	// TODO Auto-generated method stub
	public void setSprite(Image img) {
		this.sprite = new ImageView();
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
	public void hit(int damage) {
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

	/**
	 * calculate the angle from the actual direction, and ideal direction
	 * 
	 * @return
	 */
	private float calculateDir() {
	    	//Vec2 tragetDir2 = this.target.getPosition().copy();
		Vec2 tragetDir = new Vec2(1,0);//this.target.getPosition().sub(this.position);
		Vec2 dir = this.direction.copy().normalizeLocal();
		return Vec2.dot(dir, tragetDir);
	}

	/**
	 * return a new vector, from input vector rotate by degree clockwise
	 * 
	 * @param vector
	 * @param deg
	 * @return
	 */
	private Vec2 rotate(Vec2 vector, double deg) {
		float x = (float) ((vector.x * Math.cos(deg)) - (vector.y * Math.sin(deg)));
		float y = (float) ((vector.x * Math.sin(deg)) + (vector.y * Math.cos(deg)));
		return new Vec2(x, y);

	}

}
