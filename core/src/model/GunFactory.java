package model;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class GunFactory {

	public static Gun rifle() {
		// TODO Auto-generated method stub
		return new Gun() {
			private float gunRange = 30;
			private Vector2 shipPos;
			@Override
			public Bullet shot() {
				// TODO Auto-generated method stub
				return BulletFactory.BasicBullet(shipPos);
			}

			@Override
			public Boolean refreshRange(Vector2 shipPos, Vector2 direction, List<Vector2> enemyPos) {
				// TODO Auto-generated method stub
				this.shipPos = shipPos;
				return null;
			}

			@Override
			public float getDegRange() {
				// TODO Auto-generated method stub
				return this.gunRange;
			}
		};
	}

	public static Gun missile() {
		// TODO Auto-generated method stub
		return new Gun() {

			@Override
			public Bullet shot() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Boolean refreshRange(Vector2 shipPos, Vector2 direction, List<Vector2> enemyPos) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public float getDegRange() {
				// TODO Auto-generated method stub
				return 0;
			}

		};
	}

	public static Gun shootgun() {
		// TODO Auto-generated method stub
		return new Gun() {

			@Override
			public Bullet shot() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Boolean refreshRange(Vector2 shipPos, Vector2 direction, List<Vector2> enemyPos) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public float getDegRange() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

}
