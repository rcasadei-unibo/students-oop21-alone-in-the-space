package model;

import java.io.IOException;

import com.badlogic.gdx.math.Vector2;

public interface Bullet extends Entity{
	//public void move(float deltaTime);
	
	/**
	 * statement if this bullet actually has collided.
	 * @return
	 */
	public Boolean isCollided();
	
	/**
     *	destroy this bullet.
     */
	public void destroy();
	
	/**
	 * the actual damage this bullet provide on hit.
	 * @return
	 */
	public float getDamage();
	
	/**
	 * return the position of this bullet.
	 */
	public Vector2 getPosition();
	
	/**
	 * return the direction of this bullet.
	 */
	public Vector2 getDirection();
}
