package aits.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.bullet.Bullet;
import java.util.Optional;

public class BasicShip implements Ship {
	private float health;
	private float maxSpeed;
	private float acceleration;
	private float rotationSpeed;// need to be in radiant
	private Vector2 speed;
	private Vector2 direction;
	private Vector2 position;
	private Ship target;
	private Texture texture;
	private gun gun;

	public BasicShip(float health, float maxSpeed, float acceleration, float rotationSpeed) {
		super();
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
	}

	public void move(float deltaTime) {
		float cosAlfa = calculateDir();
		double a= Math.acos(cosAlfa);
		if ( a < gun.getDegRange()) {
			this.direction = this.direction
					.rotateDeg(rotationSpeed * deltaTime*(a>180?1:-1));
		}
		this.speed = this.speed.mulAdd(direction.cpy(), deltaTime*acceleration);
		if(this.speed.len2()>maxSpeed) {
			this.speed.cpy().scl(this.maxSpeed/this.speed.len());
		}
		this.position = this.position.add(this.speed);
	}

	public Optional<Bullet> shot() {
		float cosAlfa = calculateDir();
		if (Math.acos(cosAlfa) < this.gun.getDegRange()) {
			return Optional.of(BulletFactory.BasicBullet(position));
		}
		return Optional.empty();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTarget(Ship target) {
		// TODO Auto-generated method stub
		this.target = target;
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	private float calculateDir() {
		Vector2 target = this.target.getPosition().cpy().sub(this.position).nor();
		return this.direction.cpy().nor().dot(target);
	}

	@Override
	public Texture getTexture() {
		// TODO Auto-generated method stub
		return this.texture;
	}
}
