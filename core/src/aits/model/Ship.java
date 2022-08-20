package aits.model;
import java.util.Optional;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.*;
public interface Ship {
	Optional<Bullet> shot();
	void move(float deltaTime);
	void destroy();
	void setTarget(Ship target);
	Vector2 getPosition();
}
