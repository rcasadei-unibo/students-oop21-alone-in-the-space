package controller.collisionDetection;

import java.util.Collection;

import model.Bullet;
import model.Entity;
import utilities.EnumInt;

public class CollisionImpl implements Collision {

	@Override
	public boolean checkEnemyCollision(Entity ship, Entity enemy) {
		return ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent());
	}

	@Override
	public boolean checkBulletCollision(Entity ship, Bullet bullet) {
		return ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent());
	}

	@Override
	public void checkBorderCollision(Entity ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkAllCollision(Entity ship, Collection<Entity> enemies, Collection<Bullet> bullets) {
		enemies.forEach((Entity enemy) -> {
			if(checkEnemyCollision(ship, enemy)) {
				ship.decreaseLife(EnumInt.DAMAGE_COLLISION.getValue());
			}
		});
		
		bullets.forEach((Bullet bullet) -> {
			if(checkBulletCollision(ship, bullet)) {
				ship.decreaseLife(bullet.getDamage());
			}
		});

	}

}
