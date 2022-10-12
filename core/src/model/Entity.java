
package model;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public interface Entity {

	Vector2 getPosition();

	Texture getTexture();

	Boolean isAlive();

	void destroy();

	void move(float deltaTime);

	Node getNode();

	void setSprite(Image img) ;
}
