package aits.model;

import java.util.Optional;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.Bullet;

public class PlayerShip implements Ship {
	
	private Vector2 position;
	private final double maxSpeed = 0; //in both directions, for now
	private double speed = 0;
	
	@Override
	public Optional<Bullet> shot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(float deltaTime) {
		// TODO Auto-generated method stub
		try {
			position.x = (float) (position.x + speed * Math.cos(position.angleRad() * deltaTime));
			position.y = (float) (position.y + speed * Math.sin(position.angleRad() * deltaTime));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void setSpeed(double speed) {
		// TODO
		if(speed <= maxSpeed || speed >= -(maxSpeed))
			this.speed = speed;
	}
	
	public void thrustForwards(float deltaTime) {
		// TODO controller checks for if UP pressed, here it keeps adding as time passes
		double newSpeed = speed * deltaTime + 0.1;
		setSpeed(newSpeed);
	}
	
	public void thrustBackwards(float deltaTime) {
		// TODO controller checks for if UP pressed, here it keeps adding as time passes
		double newSpeed = speed * deltaTime - 0.1;
		setSpeed(newSpeed);
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

}
