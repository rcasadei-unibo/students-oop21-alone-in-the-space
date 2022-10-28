package model;

import java.util.List;
import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AbstractShip implements Ship {
	private int health;
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


	public AbstractShip(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD, Vec2 newPosition) {

		this.attackCooldown = attackCD;
		
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.position = newPosition;
		this.direction=new Vec2(0,1);

		this.speed=new Vec2(0,0);
	}

	public void move(long deltaTime) {
		
		final int halfTurn = 180; //angle degree 
		final int Turn = 360;
		double newdeltaTime = ((double)deltaTime) / 1000000000L; //conversion to seconds
		float delta = calculateDir();
		double angle =Math.toDegrees( Math.acos(delta));
		System.out.println("gradient  :::"+ angle);

		if (angle > gun.getDegRange()) {
			// how to rotate? TRUE->counterclockwise, FALSE->clockwise
			final Boolean verseOfRotation = (Turn - this.target.getPosition().copy().sub(this.position).angle()) % Turn
					+ this.direction.angle() > halfTurn;
			this.direction = rotate(direction,
					(Turn + this.rotationSpeed * newdeltaTime * (verseOfRotation ? 1 : -1)) % Turn);
		}
		else {
		    System.out.println("can shoot");
		}
		this.speed = this.speed.add(direction.copy().mul(newdeltaTime * acceleration));
		if (this.speed.length() > maxSpeed) {
			this.speed = this.speed.copy().normalize().mul(this.maxSpeed);
		}
		this.position = this.position.add(speed.copy().mul(newdeltaTime));
		System.out.println("pos :"+this.position);
		System.out.println("speed :"+this.speed);
		System.out.println("dir :"+this.direction);
		System.out.println("delta TIME :"+newdeltaTime);
	}

	public Bullet shot() {
	    	this.lastAttack -= this.attackCooldown;
		Bullet bullet = gun.shot(this.target.getPosition().sub(this.position).normalizeLocal());

		bullet.setPosition(this.position.copy());
		return bullet;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	    System.out.println("dioporco");
		this.health = 0;
		this.target = null;
	}

	@Override
	public void setTarget(Ship enemy) {
		// TODO Auto-generated method stub
	    System.out.println("target setted");
		this.target = enemy;
		this.target.getPosition().copy();
	}
	
	@Override
	public Ship getTarget() {
		return this.target;
	}

	@Override
	public Vec2 getPosition() {
		// TODO Auto-generated method stub
		return position.copy();
	}

	@Override
	public Vec2 getDirection() {
		return this.direction.copy();
	}

	@Override
	public void setPosition(Vec2 newpos) {
		this.position = newpos;
	}

	@Override
	public Boolean isInRangeOfAttack(List<Ship> enemy, long deltaTime) throws NullPointerException {
	    
		if (deltaTime - this.lastAttack > attackCooldown) {
		    this.lastAttack+=deltaTime;
		   return gun.isInRange(this.position.copy(), this.direction.copy(), enemy);
		}
		return false;
	}

	@Override
	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public void setSprite(Image img) {
		this.sprite = new ImageView();
		this.sprite.setImage(img);

	}

	@Override
	public Node getNode() {
		return this.sprite;
	}

	@Override
	public String drop() {
		return null;
	}

	@Override
	public void hit(int damage) {
		this.health -= damage;
	}

	@Override
	public Boolean isAlive() {
		// TODO Auto-generated method stub
		if (this.health > 0) {
			return true;
		}{
		    this.destroy();
		}
		return false;
	}

	/**
	 * calculate the angle from the actual direction, and ideal direction
	 * 
	 * @return
	 */
	private float calculateDir() {
	    	Vec2 tragetDir = this.target.getPosition().sub(this.position).normalizeLocal();
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
		final float x = (float) ((vector.x * Math.cos(deg)) - (vector.y * Math.sin(deg)));
		final float y = (float) ((vector.x * Math.sin(deg)) + (vector.y * Math.cos(deg)));
		return new Vec2(x, y);

	}

}
