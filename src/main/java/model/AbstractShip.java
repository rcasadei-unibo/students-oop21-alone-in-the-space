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
	private float rotationSpeed;// need to be in radiant
	private Vec2 speed;
	private Vec2 direction;
	private Vec2 position;
	private Ship target;
	private ImageView sprite;
	private Gun gun;

	public AbstractShip(float health, float maxSpeed, float acceleration, float rotationSpeed, Vec2 newPosition) {
		super();
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.position = newPosition;
	}

	public void move(float deltaTime) {
		deltaTime/=1000;
		float delta = calculateDir();
		double angle = Math.acos(delta);
		if (angle > gun.getDegRange()) {
			// how to rotate? TRUE->counterclockwise, FALSE->clockwise
			Boolean verseOfRotation = (360 - this.target.getPosition().copy().sub(this.position).angle()) % 360
					+ this.direction.angle() > 180;
			this.direction = rotate(direction,(360+ this.rotationSpeed * deltaTime * (verseOfRotation?1:-1))%360);
		}
		this.speed = this.speed.add(direction.copy().mul(deltaTime * acceleration));
		if (this.speed.length() > maxSpeed) {
			this.speed = this.speed.copy().normalize().mul(this.maxSpeed);
		}
		this.position = this.position.add(speed.copy().mul(deltaTime));
		this.sprite.setX(this.position.x);
		this.sprite.setY(this.position.y);
	}

	public Bullet shot() {
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
	public Boolean isInRangeOfAttack(List<Vec2> enemy) throws NullPointerException {
		// TODO Auto-generated method stub
		return gun.isInRange(this.position.copy(), this.direction.copy(), enemy);
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

	/**
	 * calculate the angle from the actual direction, and ideal direction
	 * 
	 * @return
	 */
	private float calculateDir() {
		Vec2 tragetDir = this.target.getPosition().copy().sub(this.position);
		// return this.direction.copy().normalize().dot(tragetDir);
		return Vec2.dot(this.direction.copy().normalize(), tragetDir);
	}
	/**
	 * return a new vector, from input vector rotate by degree clockwise
	 * @param vector
	 * @param deg
	 * @return
	 */
	private Vec2 rotate(Vec2 vector, double deg) {
		float x = (float) ((vector.x * Math.cos(deg)) - (vector.y * Math.sin(deg)));
		float y = (float) ((vector.x * Math.sin(deg)) + (vector.y * Math.cos(deg)));
		return new Vec2(x,y);

	}

}
