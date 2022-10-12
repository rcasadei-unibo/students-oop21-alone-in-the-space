package model;

import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.InputCommands;

public class PlayerShip implements Ship {

	// TODO Check movement and re-do it properly if it's baaaaad, maybe limited range shots so they don't keep going

	private Vector2 position;
	private float maxSpeed = 4.0f;
	private float speed = 0; //watch out for thread competition
	private float acceleration = 1; //static acceleration, not final
	private Texture shipTexture;
	private float rotationSpeed = 1;

	private ImageView sprite;

	private Gun playerGun;


	public Bullet shot() {
		// TODO Wrapper over AbstractPlayerGun, controller announces event
		return playerGun.shot();
	}

	public PlayerShip (Vector2 position, float maxSpeed, float acceleration, float rotationSpeed) {
		this.position = new Vector2(position);
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
	}


	public void move(float deltaTime) {
		// TODO Check
		this.setSpeed(speed + acceleration*deltaTime);
		try {
			//this.setPosition(new Vector2((float) (position.x + speed * Math.cos(position.angleRad() * deltaTime)), (float) (position.y + speed * Math.sin(position.angleRad() * deltaTime))));
			this.getPosition().x = position.x + speed * (float) Math.cos(position.angleRad()) * deltaTime;
			this.getPosition().y = position.y - speed * (float) Math.sin(position.angleRad()) * deltaTime;
		} catch (Exception e) {
			// TODO: handle exception
		}
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


	private void setSpeed(float speed) {
		// TODO maybe redundant
		if(Math.abs(speed) <= maxSpeed)
			this.speed = speed;
	}

	public void decaySpeed() {
		this.setSpeed(speed*0.99f);
	}
	
	public void thrust(InputCommands input) {
		// TODO controller checks for if UP or DOWN are pressed
		switch(input){
			case UP:
				acceleration = Math.abs(acceleration);
				break;
			case DOWN:
				acceleration = -Math.abs(acceleration);
				break;
		}
	}
	
	/*public void thrustBackwards() {
		// TODO controller checks for if DOWN pressed
		if (acceleration <= 0) acceleration = acceleration; else acceleration = -acceleration;
	}*/
	
	public void rotate(InputCommands input) {
		//Assuming degrees go back to 0 after 360
		switch(input) {
			case LEFT:
				this.getPosition().setAngleDeg(this.getPosition().angleDeg() - rotationSpeed);
				break;
			case RIGHT:
				this.getPosition().setAngleDeg(this.getPosition().angleDeg() + rotationSpeed);
				break;
		}
	}
	
/*	public void rotateLeft(float degrees) {
		//TODO ditto
		float newDegrees = this.getPosition().angleDeg() - degrees;
		if (newDegrees < 0 ) newDegrees += 360 ;
		this.getPosition().setAngleDeg(newDegrees);
	}*/


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void setTarget(Ship target) {
		// TODO might be redundant because player controlled aim, targeting seems not fun
	}

	@Override
	public void setGun(Gun gun) {

	}

	@Override
	public void hit(float damage) {

	}

	@Override
	public String drop() {
		return null;
	}

	@Override
	public Boolean isInRangeOfAttack(List<Vector2> enemy) {
		return null;
	}


	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 newPosition) {
		this.position.set(newPosition);
	}


	public Texture getTexture() {
		return shipTexture;
	}

	@Override
	public Boolean isAlive() {
		return null;
	}

	public ImageView getSprite() {
		return this.sprite;
	}


}
