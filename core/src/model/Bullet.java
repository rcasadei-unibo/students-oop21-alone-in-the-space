package model;

import com.badlogic.gdx.graphics.Texture;

public interface Bullet {
	public void move(float deltaTime);
	public Boolean isCollided();
	public void destroy();
	public float getDamage();
	public Texture getTexture();
}
