package model;

import com.almasb.fxgl.core.math.Vec2;

import java.util.List;


public interface Gun {
	/**
	 * shot a bullet in the direction specified.
	 * @param direction
	 * @return
	 */
	Bullet shot(Vec2 direction);
	
	/**
	 * check if there is at least an enemy in that direction, based on the range of the setted gun.
	 * @param shipPos
	 * @param direction
	 * @param enemyPos
	 * @return
	 */
	boolean isInRange(Vec2 shipPos, Vec2 direction, Ship enemy);
	
	/**
	 * the actual range of this gun.
	 * @return
	 */
	float getDegRange();
}
