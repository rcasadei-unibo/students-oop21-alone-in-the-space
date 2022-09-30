package controller;

public interface Collision {
	
	/**
	 * Check if there is a collision with enemies
	 * 
	 * @return True if a collision occurred
	 */
	boolean checkEnemiesCollision();
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
