package model;

import com.badlogic.gdx.math.Vector2;

public class PlayerGun implements gun {
	
	

	public Bullet shot() {
		// TODO Auto-generated method stub
		return BulletFactory.BasicBullet(null);
	}


	public Boolean range(Vector2 shipPos, Vector2 enemyPos) {
		// TODO Auto-generated method stub
		return null;
	}


	public float getDegRange() {
		// TODO Auto-generated method stub
		return 0;
	}

}
