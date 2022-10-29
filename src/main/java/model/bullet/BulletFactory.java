package model.bullet;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.Image;
import model.ship.Ship;

public class BulletFactory {

    public static Bullet BasicBullet(Vec2 position, Vec2 direction) {

	class BasicBullet extends AbstractBullet {

	    public BasicBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position,
		    Vec2 direction) {
		super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
		// TODO Auto-generated constructor stub
	    }

	};
	Bullet var = new BasicBullet(100, 100, 10, 10, position, direction);
	var.setSprite(new Image("images/bullet_01.png"));
	return var;
    }

    public static Bullet bolt(Vec2 position, Vec2 direction) {

	class Bolt extends AbstractBullet {

	    public Bolt(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position,
		    Vec2 direction) {
		super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
		// TODO Auto-generated constructor stub
	    }

	}
	;
	Bullet var = new Bolt(100, 100, 10, 10, position, direction);
	var.setSprite(new Image("images/bullet_02.png"));
	return var;

    }

    public static Bullet missile(Vec2 position, Vec2 direction, final Ship enemy) {

	class Missile extends AbstractBullet {
	    
	    protected final Ship target;
	    public Missile(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position,
		    Vec2 direction, Ship enemy) {
		super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
		// TODO Auto-generated constructor stub
		this.target = enemy;
	    }


//			@Override
//			public void move(long deltaTime) {
//				// TODO Auto-generated method stub
//				if (target.isAlive()) {
//				} else {
//					super.destroy();
//				}
//			}
	}
	;
	Bullet var = new Missile(100, 100, 10, 10, position, direction, enemy);
	var.setSprite(new Image("images/missile_01.png"));
	return var;

    }

    /**
     * PlayerBullets which change value depending on the player gun modifications
     * 
     * @param maxSpeed      top speed
     * @param acceleration  how fast it reaches its top speed
     * @param rotationSpeed if it can curve
     * @param damage        how much damage it does on impact
     * @param position      spawn position
     * @param direction     direction and trajectory to follow
     * @return bullet entity that's been shot
     */
    public static Bullet playerBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage,
	    Vec2 position, Vec2 direction) {

	class PlayerBullet extends AbstractBullet {
	    public PlayerBullet(float maxSpeed, float acceleration, float rotationSpeed, int damage, Vec2 position,
		    Vec2 direction) {
		super(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
	    }
	}
	Bullet var = new PlayerBullet(maxSpeed, acceleration, rotationSpeed, damage, position, direction);
	var.setSprite(new Image("images/bullet_00.png"));
	return var;
    }

}
