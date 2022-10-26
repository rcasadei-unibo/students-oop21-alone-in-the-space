package model;

import java.io.IOException;

import com.almasb.fxgl.core.math.Vec2;


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
	public int getDamage();
	
	/**
	 * return the position of this bullet.
	 */
	public Vec2 getPosition();
	
	/**
	 * return the direction of this bullet.
	 */
	public Vec2 getDirection();
}
