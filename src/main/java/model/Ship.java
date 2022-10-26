package model;

import com.almasb.fxgl.core.math.Vec2;

import java.util.List;

public interface Ship extends Entity{
	
	/**
	 * Generate a bullet with the current direction and position of the ship.
	 * @return
	 */
	Bullet shot();
	
	/**
	 * destroy the Ship.
	 */
	void destroy();
	
	/**
	 * Set the gun for this ship.
	 * @param gun
	 */
	void setGun(Gun gun);
	
	/**
	 * decrease the ship current health for the damage specified.
	 * @param damage
	 */
	void hit(int damage);
	
	/**
	 * set the current target of this ship.
	 * @param target
	 */
	void setTarget(Ship target);
	
	/**
	 * Drop of this ship when destroyed.
	 * @return
	 */
	String drop();
	
	/**
	 * check if at least an enemy is in range of this ship and attack cooldown is off.
	 * @param enemy
	 * @return
	 */
	Boolean isInRangeOfAttack(List<Ship> enemy, long deltaTime);
	
}
