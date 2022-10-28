package model;

import java.util.List;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.InputCommands;


public class PlayerShip implements Ship {

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

	/**
	 * constructor
	 * @param position initial position of the player
	 * @param maxHealth how much HP the ship has
	 * @param maxSpeed how fast the ship goes
	 * @param rotationSpeed how fast the ship turns
	 */
	public PlayerShip(Vec2 position, float maxHealth, float maxSpeed, float rotationSpeed) {
		this.position = new Vec2(position);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.speed = new Vec2(0, 0);
		this.maxSpeed = maxSpeed;
		this.direction = this.position.copy();
		this.rotationSpeed = rotationSpeed;
		this.rotation = new Vec2(1, 0);
		this.yaw = 0;
		calculateDir();
	}

	/**
	 * shoots a bullet from the gun
	 * @return the bullet fire
	 */
	public Bullet shot() {
		return playerGun.shot(this.position.add(rotation));
	}

	/**
	 * moves on tic update based on the direction, rotation and speed the ship has, updates position
	 * @param deltaTime time elapsed
	 */
	public void move(long deltaTime) {
		// TODO Check
		try {
			final float cosAlfa = calculateDir();
			final double a = Math.acos(cosAlfa);
			rotateDir(this.rotationSpeed * deltaTime * (a > 180 ? 1 : -1));
			this.speed.addLocal(this.direction.mul(deltaTime * this.acceleration));
			if (this.speed.lengthSquared() > this.maxSpeed) {
				this.speed.normalizeLocal().mulLocal(this.maxSpeed / this.speed.length());
			}
			this.position.addLocal(this.speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * rotate the di
	 * @param rotationAmount
	 */
	private void rotateDir(float rotationAmount) {

		final float cos = (float) Math.cos(Math.toRadians(rotationAmount));
		final float sin = (float) Math.sin(Math.toRadians(rotationAmount));


		final float newX = this.direction.x * cos - this.direction.y * sin;
		final float newY = this.direction.x * sin + this.direction.y * cos;

		this.direction.set(newX, newY);
	}

	/**
	 * calculate the new direction value that the ship has to go to
	 * @return inverse cosine value
	 */
	private float calculateDir() {
		final Vec2 currentDir = this.position.add(rotation.x, rotation.y).normalize();
		return Vec2.dot(this.direction.normalize(), currentDir);
	}

	/**
	 * slows down the ship gradually (P.S.: Space doesn't have friction for this to happen)
	 */
	public void decaySpeed() {
		this.speed.addLocal(direction.mul(0.99f));
	}
	/**
	 * change movement based on the input from player
	 */
	public void thrust(InputCommands input) {
		// TODO controller checks for if UP or DOWN are pressed
		switch (input) {
			case UP:
				acceleration = 1;
				break;
			case DOWN:
				acceleration = -1;
				break;
		}
	}
	/**
	 * when both up and down inputs aren't being held, do not accelerate
	 */
	public void thrustReleased() {
		this.acceleration = 0;
	}

	/**
	 * rotate the ship on its position depending on the inputs
	 * @param input user input
	 */
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
		this.yaw = Math.abs((yaw + rotationSpeed * direction)) % 360;
		this.rotation.setFromAngle(yaw);
	}

	/**
	 * getter
	 * @return angle of the ship
	 */
	public double getYaw() {
		return this.yaw;
	}

	/**
	 * getter
	 * @return direction of the ship
	 */
	public Vec2 getDirection() {
		return this.direction;
	}

	/**
	 * not useful for Player ship, potentially reworkable for new weapon type
	 * @param target
	 */
	public void setTarget(Ship target) {
		// TODO might be redundant because player controlled aim, targeting seems not fun
	}

	/**
	 * setter
	 * @param gun Gun for the player ship
	 */
	@Override
	public void setGun(Gun gun) {
		this.playerGun = gun;
	}

	/**
	 * lose health based on the damage inflicted
	 * @param damage inflicted damage amount
	 */
	@Override
	public void hit(int damage) {
		this.health -= damage;
		if (this.health <= 0) {
			this.destroy();
		}
	}

	/**
	 * not useful for player, dedicated to enemy ships, potentially reworkable to drop the player gun
	 * @return null
	 */
	@Override
	public String drop() {
		return null;
	}

	/**
	 * not useful for player, potentially reworkable for new gun type
	 * @param enemy
	 * @param deltaTime
	 * @return
	 */
	@Override
	public Boolean isInRangeOfAttack(List<Ship> enemy, long deltaTime) {
		return null;
	}

	/**
	 * getter
	 * @return current player position
	 */
	public Vec2 getPosition() {
		return position;
	}

	/**
	 * setter
	 * @param newPosition set new position of player
	 */
	public void setPosition(Vec2 newPosition) {
		this.position.set(newPosition);
	}

	/**
	 * destroys the playerShip
	 */
	public void destroy() {
		this.health = 0;
	}

	/**
	 * check if the ship still has health left
	 * @return
	 */
	@Override
	public Boolean isAlive() {
		return this.health > 0;
	}

	/**
	 * getter
	 * @return current health of the player
	 */
	public float getHealth() {
		return this.health;
	}

	/**
	 * getter
	 * @return current max health of the player
	 */
	public float getMaxHealth() {
	    return this.maxHealth; 
	}

	/**
	 * setter
	 * @param hitPoints what health amount to set the player at
	 */
	public void setHealth(int hitPoints) {
		this.health = hitPoints;
	}

	/**
	 * heals the player up until max health
	 * @param hitPoints how much hp healed
	 */
	public void heal(int hitPoints) {
		this.health = Math.min((this.health + hitPoints), maxHealth);
	}

	/**
	 * getter
	 * @return node-imageview of the player
	 */
	@Override
	public Node getNode() {
		return this.sprite;
	}

	/**
	 * getter
	 * @return sprite of the player
	 */
	public ImageView getSprite() {
		return this.sprite;
	}

	/**
	 * setter
	 * @param img new sprite for the player
	 */
	@Override
	public void setSprite(Image img) {
		this.sprite = new ImageView();
		this.sprite.setImage(img);
	}

	/**
	 * setter
	 * @param maxHealth new max health the player can reach
	 */
	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	@Override
	public Ship getTarget() {
	    // TODO Auto-generated method stub
	    return null;
	}
}
