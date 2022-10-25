package model;

import java.util.List;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.EnumInt;
import utilities.InputCommands;
import utilities.PlayerGunValues;
import utilities.PlayerValues;


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
	private float fireRate;
	private int exp = 0;
	private int score = 0;
	private int currentLives;
	private final int currentLevel;


	public PlayerShip (Vec2 position, float maxHealth, float maxSpeed, float fireRate, float rotationSpeed) {
		this.position = new Vec2(position);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.speed = new Vec2(0,0);
		this.maxSpeed = maxSpeed;
		this.fireRate = fireRate;
		this.direction = this.position.copy();
		this.rotationSpeed = rotationSpeed;
		this.rotation = new Vec2(1, 0);
		this.yaw = 0;
		this.currentLives = 3;
		this.currentLevel = 1;
		calculateDir();
	}

	public Bullet shot() {
		return playerGun.shot(this.position.add(rotation));
	}

	public void move(float deltaTime) {
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
			// TODO: handle exception
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
		this.rotation.setFromAngle(yaw);
	}


	public Vec2 getDirection() {
		return this.direction;
	}

	public void destroy() {
		this.currentLives--;
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

	@Override
	public Boolean isAlive() {
		return this.health <= 0 ? true : false;
	}

	public int getCurrentLives() {
		return this.currentLives;
	}

	public void addLives() {
		this.currentLives++;
	}

	public float getHealth() {
		return this.health;
	}
	public void setHealth(float hitPoints) {
		this.health = hitPoints;
	}

	public void heal(float hitPoints) {
		this.health = (this.health + hitPoints) > maxHealth ? maxHealth : (this.health + hitPoints);
	}


	public double getYaw() {
		return this.yaw;
	}

	public int getExp() {
		return this.exp;
	}
	public int getScore() {
		return this.score;
	}

	public void setExp(int newPoints) {
		this.exp = newPoints;
	}
	public void setScore(int newScore) {
		this.score = newScore;
	}
	public void addScoreExp(int expGained) {
		this.score += expGained;
		this.exp += expGained;
		if(checkLevelUp()) {
			this.levelUp();
		}
	}

	public void levelUp() {
		this.maxHealth += 5 * (PlayerValues.MAIN_SHIP.getValueFromKey("MAXHEALTH"))/100;
		this.health = this.maxHealth;

		if(this.currentLevel % 3 == 0) {
			this.fireRate += 5 * (PlayerValues.MAIN_SHIP.getValueFromKey("FIRERATE"))/100;
		}

		if(this.currentLevel % 5 == 0) {
			this.gunLevelUp(5 * (PlayerGunValues.MAIN_GUN.getValueFromKey("DAMAGE"))/100);
		}

		this.exp -= EnumInt.EXP_REQUIRED.getValue()*(Math.pow(2, this.currentLevel-1));
	}

	private void gunLevelUp(float newDamage) {
		this.setGun(GunFactory.playerGun(this, newDamage, PlayerGunValues.MAIN_GUN.getValueFromKey("MAXSPEED"),
				PlayerGunValues.MAIN_GUN.getValueFromKey("ACCELERATION"), PlayerGunValues.MAIN_GUN.getValueFromKey("ROTATIONSPEED")));
	}

	public boolean checkLevelUp() {
		return this.exp >= EnumInt.EXP_REQUIRED.getValue() * (Math.pow(2, this.currentLevel - 1));
	}
}
