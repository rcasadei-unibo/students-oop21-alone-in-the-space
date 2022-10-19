package controller.collisionDetection;

import java.util.Collection;

import model.Bullet;
import model.Entity;

public interface Collision {
	
	/**
	 * Check if there is a collision with enemies
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkEnemyCollision(Entity ship, Entity enemy);
	/**
	 * Check if there is a collision with bullets
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkBulletCollision(Entity ship, Bullet bullet);
	/**
	 * Check collision with enemies and bullets
	 * @param ship
	 * @param enemies
	 * @param bullets
	 */
	void checkAllCollision(Entity ship, Collection<Entity> enemies, Collection<Bullet> bullets);
	
	/**
	 * Check if there is a collision with borders
	 */
	void checkBorderCollision(Entity ship);
	
}
