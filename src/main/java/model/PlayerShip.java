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
	private float acceleration;
	private Vec2 direction;
	private Texture shipTexture;
	private float rotationSpeed;
	private ImageView sprite;
	private Gun playerGun;
	private Node node;
	private Vec2 rotation;
	private float yaw;


	public Bullet shot() {
		return playerGun.shot(rotation);
	}

	public PlayerShip (Vec2 position, float maxHealth, float maxSpeed, float acceleration, float rotationSpeed) {
		this.position = new Vec2(position);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
		this.rotation = new Vec2(1, 0);
		this.yaw = 0;
		this.rotation.setAngleDeg(yaw);
		calculateDir();
	}



	public void move(float deltaTime) {
		// TODO Check
		try {
			float cosAlfa = calculateDir();
			double a = Math.acos(cosAlfa);
			this.direction = this.direction.rotateDeg(rotationSpeed * deltaTime * (a > 180 ? 1 : -1));
			this.speed = this.speed.mulAdd(direction.cpy(), deltaTime * acceleration);
			if (this.speed.len2() > maxSpeed) {
				this.speed.cpy().scl(this.maxSpeed / this.speed.len());
			}
			this.position = this.position.add(this.speed);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private float calculateDir() {
		Vec2 currentDir = this.position.cpy().add(rotation.x,rotation.y).sub(this.position).nor();
		return this.direction.cpy().nor().dot(currentDir);
	}

	@Override
	public Node getNode() {
		return null;
	}

	@Override
	public void setSprite(Image img) {
		this.sprite = new ImageView();
		this.sprite.setImage(img);
	}


	public void decaySpeed() {
		this.speed.mulAdd(direction.cpy(), 0.99f);
	}
	
	public void thrust(InputCommands input) {
		// TODO controller checks for if UP or DOWN are pressed
		switch(input){
			case UP:
				acceleration = Math.abs(acceleration == 0 ? 1 : acceleration);
				break;
			case DOWN:
				acceleration = -Math.abs(acceleration == 0 ? 1 : acceleration);
				break;
		}
	}

	public void thrustReleased() {
		this.acceleration = 0;
	}

	
	public void rotate(InputCommands input, float deltaTime) {
		int direction = 1;
		switch (input) {
			case LEFT:
				direction = Math.abs(direction);
				break;
			case RIGHT:
				direction = -direction;
				break;
		}
		this.yaw = Math.abs((yaw + rotationSpeed*deltaTime*direction)) % 360;
		this.rotation.set((float)Math.cos(yaw), -(float)Math.sin(yaw));
	}


	public Vec2 getDirection() {
		return this.direction;
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void setTarget(Ship target) {
		// TODO might be redundant because player controlled aim, targeting seems not fun
	}

	@Override
	public void setGun(Gun gun) {
		this.playerGun = gun;
	}

	@Override
	public void hit(float damage) {
		this.health -= damage;
	}

	@Override
	public String drop() {
		return null;
	}

	@Override
	public Boolean isInRangeOfAttack(List<Vec2> enemy) {
		return null;
	}


	public Vec2 getPosition() {
		return position;
	}
	
	public void setPosition(Vec2 newPosition) {
		this.position.set(newPosition);
	}


	public Texture getTexture() {
		return shipTexture;
	}

	@Override
	public Boolean isAlive() {
		return this.health == 0 ? true : false;
	}

	public ImageView getSprite() {
		return this.sprite;
	}


}
