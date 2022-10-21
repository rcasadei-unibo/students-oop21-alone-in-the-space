package model;

import java.util.List;

import com.badlogic.gdx.math.Vector2;

interface Gun {
	/**
	 * shot a bullet in the direction specified.
	 * @param direction
	 * @return
	 */
	public Bullet shot(Vector2 direction);
	
	/**
	 * check if there is at least an enemy in that direction, based on the range of the setted gun.
	 * @param shipPos
	 * @param direction
	 * @param enemyPos
	 * @return
	 */
	public Boolean isInRange(Vector2 shipPos,Vector2 direction,List<Vector2> enemyPos); 
	
	/**
	 * the actual range of this gun.
	 * @return
	 */
	public float getDegRange();
}
