package model;

import java.util.List;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.InputCommands;


public class PlayerShip implements Ship {



	// TODO Check movement and re-do it properly if it's baaaaad, maybe limited range shots so they don't keep going

	private Vec2 position;
	private float maxHealth;
	private float health;
	private float maxSpeed;
	private Vec2 speed; //watch out for thread competition
	private float acceleration = 0;
	private Vec2 direction;
	private float rotationSpeed;
	private ImageView sprite;
	private Gun playerGun;
	private Vec2 rotation;
	private float yaw;


	public PlayerShip (Vec2 position, float maxHealth, float maxSpeed, float rotationSpeed) {
		this.position = new Vec2(position);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.speed = new Vec2(0,0);
		this.maxSpeed = maxSpeed;
		this.direction = this.position.copy();
		this.rotationSpeed = rotationSpeed;
		this.rotation = new Vec2(1, 0);
		this.yaw = 0;
		calculateDir();
	}


	public Bullet shot() {
		return playerGun.shot(this.position.add(rotation));
	}

	public void move(long deltaTime) {
		// TODO Check

		try {
			float cosAlfa = calculateDir();
			double a = Math.acos(cosAlfa);
			rotateDir(rotationSpeed * deltaTime * (a > 180 ? 1 : -1));
			this.speed.addLocal(direction.mul(deltaTime * acceleration));
			if (this.speed.lengthSquared() > maxSpeed) {
				this.speed.normalizeLocal().mulLocal(this.maxSpeed / this.speed.length());
			}
			this.position.addLocal(this.speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rotateDir(float rotationAmount) {
		float cos = (float)Math.cos(Math.toRadians(rotationAmount));
		float sin = (float)Math.sin(Math.toRadians(rotationAmount));

		float newX = this.direction.x * cos - this.direction.y * sin;
		float newY = this.direction.x * sin + this.direction.y * cos;

		this.direction.set(newX, newY);
	}

	private float calculateDir() {
		Vec2 currentDir = this.position.add(rotation.x,rotation.y).normalize();
		return Vec2.dot(this.direction.normalize(), currentDir);
	}

	public void decaySpeed() {
		this.speed.addLocal(direction.mul(0.99f));
	}
	public void thrust(InputCommands input) {
		// TODO controller checks for if UP or DOWN are pressed
		switch(input){
			case UP:
				acceleration = 1;
				break;
			case DOWN:
				acceleration = -1;
				break;
		}
	}
	public void thrustReleased() {
		this.acceleration = 0;
	}
	public void rotate(InputCommands input) {
		int direction = 1;
		switch (input) {
			case LEFT:
				direction = Math.abs(direction);
				break;
			case RIGHT:
				direction = -Math.abs(direction);
				break;
		}
		this.yaw = Math.abs((yaw + rotationSpeed*direction)) % 360;
		this.rotation.setFromAngle(yaw);
	}
	public double getYaw() {
		return this.yaw;
	}
	public Vec2 getDirection() {
		return this.direction;
	}

	public void setTarget(Ship target) {
		// TODO might be redundant because player controlled aim, targeting seems not fun
	}
	
	@Override
	public void setGun(Gun gun) {
		this.playerGun = gun;
	}
	@Override
	public void hit(int damage) {
		this.health -= damage;
		if(this.health <= 0) {
			this.destroy();
		}
	}
	@Override
	public String drop() {
		return null;
	}
	@Override
	public Boolean isInRangeOfAttack(List<Vec2> enemy, long deltaTime) {
		return null;
	}
	public Vec2 getPosition() {
		return position;
	}
	
	public void setPosition(Vec2 newPosition) {
		this.position.set(newPosition);
	}
	public void destroy() {

	}
	@Override
	public Boolean isAlive() {
		return this.health <= 0 ? true : false;
	}
	public float getHealth() {
		return this.health;
	}
	public float getMaxHealth() { return this.maxHealth; }
	public void setHealth(float hitPoints) {
		this.health = hitPoints;
	}

	public void heal(float hitPoints) {
		this.health = Math.min((this.health + hitPoints), maxHealth);
	}

	@Override
	public Node getNode() {
		return this.sprite;
	}

	public ImageView getSprite() {
		return this.sprite;
	}

	@Override
	public void setSprite(Image img) {
		this.sprite = new ImageView();
		this.sprite.setImage(img);
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}
}
