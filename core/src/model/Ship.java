package model;

import java.io.IOException;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public interface Ship extends Entity{
	
	/**
     *	Generate a bullet with the current direction and position of the ship.
     *
     * @throws IOException
     */
	Bullet shot();
	
	/**
     *	destroy the Ship.
     *
     * @throws IOException
     */
	void destroy();
	
	/**
     *	Set the gun for this ship.
     *
     * @throws IOException
     */
	void setGun(Gun gun);
	
	/**
     *	decrease the ship current health for the damage specified.
     *
     * @throws IOException
     */
	void hit(float damage);
	
	/**
     *	set the current target of this ship.
     *
     * @throws IOException
     */
	void setTarget(Ship target);
	
	/**
     *	Drop of this ship when destroyed.
     *
     * @throws IOException
     */
	String drop();
	
	/**
     *	check if at least an enemy is in range of this ship.
     *
     * @throws IOException
     */
	Boolean isInRangeOfAttack(List<Vector2> enemy);
	
}
