package model.ship;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.bullet.Bullet;
import model.gun.Gun;
import utilities.InputCommands;


public class PlayerShip implements Ship {

	private Vec2 position;
	private int maxHealth;
	private int health;
	private int maxSpeed;
	private Vec2 speed; //watch out for thread competition
	private float speed2 = 0;
	private float acceleration = 0;
	private Vec2 direction;
	private int rotationSpeed;
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
	public PlayerShip(Vec2 position, int maxHealth, int maxSpeed, int rotationSpeed) {
		this.position = new Vec2(position);
		this.maxHealth = maxHealth;
		this.health = this.maxHealth;
		this.speed = new Vec2(0, 0);
		this.maxSpeed = maxSpeed;
		this.direction = this.position.copy();
		this.rotationSpeed = rotationSpeed;
		this.rotation = new Vec2(0, 1);
		this.yaw = 1;
		calculateDir();
	}

	/**
	 * shoots a bullet from the gun
	 * @return the bullet fire
	 */
	public Bullet shot() {
		return playerGun.shot(this.direction);
	}

	/**
	 * moves on tic update based on the direction, rotation and speed the ship has, updates position
	 * @param deltaTime time elapsed
	 */
	public void move(long deltaTime) {
		// TODO Check
		float newSpeed = speed2 + acceleration * 0.01f * ((float)deltaTime)/1_000_000L;
		this.setSpeed(newSpeed);
		try {
			float newX = (float) (speed2 * Math.cos(Math.toRadians(yaw)));
			float newY = (float) (speed2 * Math.sin(Math.toRadians(yaw)));
			this.direction.set(this.direction.x + newX*1.01f, this.direction.y + newY*1.01f);
			this.position.addLocal(newX,newY);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void setSpeed(float newSpeed) {
		if(newSpeed >= maxSpeed)
			this.speed2 = maxSpeed;
		else if(newSpeed <= -maxSpeed){
			this.speed2 = -maxSpeed;
		}
		else
			this.speed2 = newSpeed;
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
		this.setSpeed(speed2*0.95f);
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
		int direction = -1;
		switch (input) {
			case LEFT:
				direction = -Math.abs(direction);
				break;
			case RIGHT:
				direction = Math.abs(direction);
				break;
		}
		this.yaw = (yaw + rotationSpeed * direction) % 360;
		float newX = (float) (Math.cos(Math.toRadians(yaw)));
		float newY = (float) (Math.sin(Math.toRadians(yaw)));
		this.direction.set(this.position.x + newX*1.01f, this.position.y + newY*1.01f);
	}

	/**
	 * getter
	 * @return angle of the ship
	 */
	@Override
	public double getAngle() {
		return this.yaw;
	}

	/**
	 *
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
	 * @param deltaTime
	 * @return
	 */
	@Override
	public Boolean isInRangeOfAttack( long deltaTime) {
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
	 *
	 * @return current health of the player
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * getter
	 * @return current max health of the player
	 */
	public int getMaxHealth() {
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
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public float getAcceleration() {
		return this.acceleration;
	}

	@Override
	public Ship getTarget() {
	    // TODO Auto-generated method stub
	    return null;

	}

}
