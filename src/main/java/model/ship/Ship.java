package model.ship;

import java.util.List;

import model.Entity;
import model.bullet.Bullet;
import model.gun.Gun;

public interface Ship extends Entity {
	
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
	void strike(int damage);
	
	/**
	 * set the current target of this ship.
	 * @param target
	 */
	void setTarget(Ship target);
	
	/**
	 * get the current target of this ship.
	 * @return target
	 */
	Ship getTarget();
	
	
	
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
	Boolean isInRangeOfAttack( long deltaTime);
	
}
