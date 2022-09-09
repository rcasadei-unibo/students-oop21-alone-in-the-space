package model;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayerShip implements Ship {

	// TODO Check movement and re-do it properly if it's baaaaad, maybe limited range shots so they don't keep going

	private Vector2 position;
	private float maxSpeed = 35; //in both directions, for now
	private float speed = 0; //watch out for thread competition
	private float acceleration = 1; //static acceleration, not final
	private Texture shipTexture;



	public Optional<Bullet> shot() {
		// TODO Wrapper over AbstractPlayerGun, controller announces event
		Vector2 bulletFiringPosition = new Vector2(position);
		bulletFiringPosition.x += 1 * Math.cos(bulletFiringPosition.angleRad());
		bulletFiringPosition.y += 1 * Math.sin(bulletFiringPosition.angleRad());
		return Optional.of(BulletFactory.PlayerBasicBullet(bulletFiringPosition));
	}


	public void move(float deltaTime) {
		// TODO Check
		float newSpeed = speed + acceleration*deltaTime;
		this.setSpeed(newSpeed);
		try {
			//this.setPosition(new Vector2((float) (position.x + speed * Math.cos(position.angleRad() * deltaTime)), (float) (position.y + speed * Math.sin(position.angleRad() * deltaTime))));
			this.getPosition().x = (float) (position.x + speed * Math.cos(position.angleRad() * deltaTime));
			this.getPosition().y = (float) (position.y + speed * Math.sin(position.angleRad() * deltaTime));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void setSpeed(float speed) {
		// TODO maybe redundant
		if(speed < maxSpeed || speed > -(maxSpeed))
			this.speed = speed;
	}
	
	public void thrustForwards() {
		// TODO controller checks for if UP pressed
		if (acceleration >= 0) acceleration = acceleration; else acceleration = -acceleration;
	}
	
	public void thrustBackwards() {
		// TODO controller checks for if DOWN pressed
		if (acceleration <= 0) acceleration = acceleration; else acceleration = -acceleration;
	}
	
	public void rotateRight(float degrees) {
		//TODO redo? i kinda don't like
		float newDegrees = this.getPosition().angleDeg() + degrees;
		if (newDegrees >= 360) newDegrees -= 360 ;
		this.getPosition().setAngleDeg(newDegrees);
	}
	
	public void rotateLeft(float degrees) {
		//TODO ditto
		float newDegrees = this.getPosition().angleDeg() - degrees;
		if (newDegrees < 0 ) newDegrees += 360 ;
		this.getPosition().setAngleDeg(newDegrees);
	}


	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void setTarget(Ship target) {
		// TODO might be redundant because player controlled aim, targeting seems not fun
		
	}


	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setPosition(Vector2 newPosition) {
		this.position.set(newPosition);
	}


	public Texture getTexture() {
		return shipTexture;
	}
	
	

}
