package aits.model;

import com.badlogic.gdx.physics.bullet.Bullet;

public class EnemyFactory {
	public static Ship BasicEnemy() {
		return new BasicShip(100,10,2,60);	
	}
}
