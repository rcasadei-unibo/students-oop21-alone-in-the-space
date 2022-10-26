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
	boolean checkEnemyCollision(Ship ship, Ship enemy);
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
	 * @param playerBullets
	 * @param enemiesBullets
	 */
	void checkAllCollision(Ship ship, Collection<Ship> enemies, Collection<Bullet> playerBullets,
			Collection<Bullet> enemiesBullets);
	
	/**
	 * Check if there is a collision with borders
	 */
	void checkBorderCollision(Entity ship);
	
}
