package controller;

import javafx.scene.shape.Rectangle;
import model.Entity;

public interface Collision {
	
	/**
	 * Check if there is a collision with enemies
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkEnemiesCollision(Entity ship, Entity enemy);
	/**
	 * Check if there is a collision with bullets
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkBulletsCollision(Entity ship, Entity bullet);
	/**
	 * Check if there is a collision with borders
	 */
	void checkBorderCollision(Entity ship);
	
}
