
package model;

import com.almasb.fxgl.core.math.Vec2;
import javafx.scene.Node;
import javafx.scene.image.Image;

public interface Entity {
	
    /**
	 * get the current angle of this ship.
	 * @return angle in degrees
	 */
	double getAngle();
	
	 /**
	  * Get the position of the entity
	  * @return
	  */
	Vec2 getPosition();
	
	 /**
	  * Get the direction of the entity
	  * @return
	  */
	Vec2 getDirection();
	
	 /**
	  * boolean statement if the entity is still alive
	  * @return
	  */
	Boolean isAlive();
	
	 /**
	  * destroy all item attached to the object
	  */
	void destroy();

	 /**
	  * move the entity for the interval specified
	  * @param deltaTime
	  */
	void move(long deltaTime);

	 /**
	  * return the node (of javaFX) associate at the object
	  * @return
	  */
	Node getNode();

	 /**
	  * Set the sprite of the entity
	  * @param img
	  */
	void setSprite(Image img);
	
	 /**
	  * Set the position.
	  * @param newpos
	  */
	void setPosition(Vec2 newpos);
}
