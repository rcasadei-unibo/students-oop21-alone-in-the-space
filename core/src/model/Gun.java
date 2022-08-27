package model;

import com.badlogic.gdx.math.Vector2;

interface gun {
	public Bullet shot();
	public Boolean range(Vector2 shipPos,Vector2 enemyPos); 
	public float getDegRange();
}
