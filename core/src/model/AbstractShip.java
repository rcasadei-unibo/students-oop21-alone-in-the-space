package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AbstractShip implements Enemy {
	private float health;
	private float maxSpeed;
	private float acceleration;
	private float rotationSpeed;// need to be in radiant
	private Vector2 speed;
	private Vector2 direction;
	private Vector2 position;
	private Ship target;
	private Texture texture;
	private Image sprite;
	private Gun gun;
	private List<Bullet> projectile = new ArrayList<>();

	public AbstractShip(float health, float maxSpeed, float acceleration, float rotationSpeed) {
		super();
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.rotationSpeed = rotationSpeed;
	}

	public void move(float deltaTime) {
		float cosAlfa = calculateDir();
		double a = Math.acos(cosAlfa);
		if (a < gun.getDegRange()) {
			this.direction = this.direction.rotateDeg(rotationSpeed * deltaTime * (a > 180 ? 1 : -1));
		}
		this.speed = this.speed.mulAdd(direction.cpy(), deltaTime * acceleration);
		if (this.speed.len2() > maxSpeed) {
			this.speed.cpy().scl(this.maxSpeed / this.speed.len());
		}
		this.position = this.position.add(this.speed);
	}

	public Bullet shot() {
		return gun.shot();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.health = 0;
		this.projectile.forEach(e -> e.destroy());
		this.projectile.clear();
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

	public Texture getTexture() {
		// TODO Auto-generated method stub
		return this.texture;
	}

	@Override
	public Boolean isInRangeOfAttack(List<Vector2> enemy) throws NullPointerException {
		// TODO Auto-generated method stub
		return gun.refreshRange(this.position.cpy(), this.direction.cpy(), enemy);
	}

	@Override
	public void setGun(Gun gun) {
		// TODO Auto-generated method stub
		this.gun = gun;
	}

	public void setSprite(Image img) throws IllegalArgumentException, NullPointerException {
		// TODO Auto-generated method stub
		this.sprite=img;

	}

	@Override
	public String drop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hit(float damage) {
		// TODO Auto-generated method stub
		this.health -= damage;
	}

	@Override
	public Boolean isAlive() {
		// TODO Auto-generated method stub
		if (this.health > 0) {
			return true;
		}
		this.destroy();
		return false;
	}

	@Override
	public Node getNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
