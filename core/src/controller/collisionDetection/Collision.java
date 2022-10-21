package controller.collisionDetection;

import java.util.Collection;

import model.Bullet;
import model.Entity;
import model.Ship;

public interface Collision {
	
	/**
	 * Check if there is a collision with enemies
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkEnemyCollision(Ship ship, Entity enemy);
	/**
	 * Check if there is a collision with bullets
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkBulletCollision(Ship ship, Bullet bullet);
	/**
	 * Check collision with enemies and bullets
	 * @param ship
	 * @param enemies
	 * @param bullets
	 */
	void checkAllCollision(Ship ship, Collection<Entity> enemies, Collection<Bullet> bullets);
	
	/**
	 * Check if there is a collision with borders
	 */
	void checkBorderCollision(Entity ship);
	
}
