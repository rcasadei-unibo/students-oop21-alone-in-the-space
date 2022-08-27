package model;
import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
public interface Ship {
	Optional<Bullet> shot();
	void move(float deltaTime);
	void destroy();
	void setTarget(Ship target);
	Vector2 getPosition();
	Texture getTexture();
}
