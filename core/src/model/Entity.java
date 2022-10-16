
package model;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
	
	 /**
     * Get the position of the entity
     *
     */
	Vector2 getPosition();
	
	 /**
     * Get the direction of the entity
     *
     */
	Vector2 getDirection();
	
	 /**
     * boolean statement if the entity is still alive
     *
     */
	Boolean isAlive();
	
	 /**
     * destroy all item attached to the object
     *
     */
	void destroy();

	 /**
     * move the entity for the interval specified
     *
     */
	void move(float deltaTime);

	 /**
     * return the node (of javaFX) associate at the object
     *
     */
	Node getNode();

	 /**
     *	Set the sprite of the entity
     *
     */
	void setSprite(Image img);
	
	 /**
     * Set the position.
     *
     */
	void setPosition(Vector2 newpos);
}
