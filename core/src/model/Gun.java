package model;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

interface Gun {
	public Bullet shot();
	public Boolean refreshRange(Vector2 shipPos,Vector2 direction,List<Vector2> enemyPos); 
	public float getDegRange();
}
