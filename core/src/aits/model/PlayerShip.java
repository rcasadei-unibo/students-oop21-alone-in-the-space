package aits.model;

import java.util.Optional;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.Bullet;

public class PlayerShip implements Ship {
	
	private Vector2 position;
	private final double maxSpeed = 0; //in both directions, for now
	private double speed = 0;
	private double acceleration = 0;
	private final double maxAcceleration = 0;
	
	@Override
	public Optional<Bullet> shot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(float deltaTime) {
		// TODO Auto-generated method stub
		double newSpeed = speed + acceleration*deltaTime;
		this.setSpeed(newSpeed);
		try {
			//this.setPosition(new Vector2((float) (position.x + speed * Math.cos(position.angleRad() * deltaTime)), (float) (position.y + speed * Math.sin(position.angleRad() * deltaTime))));
			this.getPosition().x = (float) (position.x + speed * Math.cos(position.angleRad() * deltaTime));
			this.getPosition().y = (float) (position.y + speed * Math.sin(position.angleRad() * deltaTime));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void setSpeed(double speed) {
		// TODO
		if(speed < maxSpeed || speed > -(maxSpeed))
			this.speed = speed;
	}
	
	public void thrustForwards() {
		// TODO controller checks for if UP pressed; in here acceleration keeps increasing the longer you press
		if (this.acceleration < maxAcceleration) acceleration += 0.01;
	}
	
	public void thrustBackwards() {
		// TODO controller checks for if DOWN pressed; in here acceleration keeps decreasing the longer you press
		if (this.acceleration < maxAcceleration) acceleration -= 0.01;
	}
	
	public void rotateRight(float degrees) {
		float newDegrees = this.getPosition().angleDeg() + degrees;
		if (newDegrees >= 360) newDegrees -= 360 ;
		this.getPosition().setAngleDeg(newDegrees);
	}
	
	public void rotateLeft(float degrees) {
		float newDegrees = this.getPosition().angleDeg() - degrees;
		if (newDegrees < 0 ) newDegrees += 360 ;
		this.getPosition().setAngleDeg(newDegrees);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTarget(Ship target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setPosition(Vector2 newPosition) {
		this.position.set(newPosition);
	}

}
