package model;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class PlayerGun implements Gun {
	
	

	public Bullet shot() {
		// TODO Auto-generated method stub
		return BulletFactory.BasicBullet(null);
	}

	@Override
	public Boolean refreshRange(Vector2 shipPos, Vector2 direction, List<Vector2> enemyPos) {
		return null;
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
