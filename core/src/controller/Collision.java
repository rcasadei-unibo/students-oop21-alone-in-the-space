package controller;

import javafx.scene.shape.Rectangle;
import model.Entity;

public interface Collision {
	
	/**
	 * Check if there is a collision with enemies
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkEnemiesCollision(Entity e, Rectangle r);
	/**
	 * Check if there is a collision with bullets
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkBulletsCollision();
	/**
	 * Check if there is a collision with borders
	 */
	void checkBorderCollision();
	
}
