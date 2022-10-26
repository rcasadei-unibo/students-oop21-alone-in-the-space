package model;

import java.util.List;

import com.almasb.fxgl.core.math.Vec2;


interface Gun {
	/**
	 * shot a bullet in the direction specified.
	 * @param direction
	 * @return
	 */
	public Bullet shot(Vec2 direction);
	
	/**
	 * check if there is at least an enemy in that direction, based on the range of the setted gun.
	 * @param shipPos
	 * @param direction
	 * @param enemyPos
	 * @return
	 */
	public Boolean isInRange(Vec2 shipPos,Vec2 direction,List<Ship> enemy);
	
	/**
	 * the actual range of this gun.
	 * @return
	 */
	public float getDegRange();
}
