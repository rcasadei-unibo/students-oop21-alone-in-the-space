package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
	public Vector2 getPosition();
	public Texture getTexture();
	public Boolean isAlive();
	public void destroy();
	void move(float deltaTime);
}
