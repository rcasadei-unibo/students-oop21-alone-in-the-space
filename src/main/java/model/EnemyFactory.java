package model;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.image.Image;

public class EnemyFactory {
    public static Ship BasicEnemy(Vec2 newPosition) throws IllegalArgumentException {
	class BasicEnemy extends AbstractShip {

	    public BasicEnemy(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
		    Vec2 position) {
		super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
		// TODO Auto-generated constructor stub
	    }

	}
	;
	Ship en = new BasicEnemy(50, 100, 20f, 450, 1000000, newPosition);
	en.setGun(GunFactory.missile(en));
	en.setSprite(new Image("images/ship_0.png"));
	return en;

    }

    public static Ship MissileEnemy(Vec2 newPosition) throws IllegalArgumentException {
	class MissileEnemy extends AbstractShip {

	    public MissileEnemy(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
		    Vec2 position) {
		super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
		// TODO Auto-generated constructor stub
	    }

	}
	;
	Ship en = new MissileEnemy(100, 50, 5f, 100, 2000000, newPosition);
	en.setGun(GunFactory.missile(en));
	en.setSprite(new Image("images/ship_30.png"));
	return en;

    }

    public static Ship RifleEnemy(Vec2 newPosition) throws IllegalArgumentException {
	class RifleEnemy extends AbstractShip {

	    public RifleEnemy(int health, float maxSpeed, float acceleration, float rotationSpeed, long attackCD,
		    Vec2 position) {
		super(health, maxSpeed, acceleration, rotationSpeed, attackCD, position);
		// TODO Auto-generated constructor stub
	    }

	}
	;
	Ship en = new RifleEnemy(25, 150, 10f, 300, 500000, newPosition);
	en.setGun(GunFactory.rifle(en));
	en.setSprite(new Image("images/ship_22.png"));
	return en;
    }

}