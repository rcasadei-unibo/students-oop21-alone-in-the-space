package aits.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.BulletBase;

interface gun {
	public BulletBase shot();
	public Boolean range(Vector2 shipPos,Vector2 enemyPos); 
	public float getDegRange();
}
