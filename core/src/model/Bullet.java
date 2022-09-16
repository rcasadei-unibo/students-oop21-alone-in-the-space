package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public interface Bullet extends Entity{
	//public void move(float deltaTime);
	public Boolean isCollided();
	public void destroy();
	public float getDamage();
	public Vector2 getPosition();
	public Vector2 getDirection();
	public Texture getTexture();
}
