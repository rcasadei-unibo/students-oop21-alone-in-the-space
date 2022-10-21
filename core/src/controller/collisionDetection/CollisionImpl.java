package controller.collisionDetection;

import java.util.Collection;

import model.Bullet;
import model.Entity;
import model.Ship;
import utilities.EnumInt;

public class CollisionImpl implements Collision {

	@Override
	public boolean checkEnemyCollision(Ship ship, Entity enemy) {
		return ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent());
	}

	@Override
	public boolean checkBulletCollision(Ship ship, Bullet bullet) {
		return ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent());
	}

	@Override
	public void checkBorderCollision(Entity ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkAllCollision(Ship ship, Collection<Entity> enemies, Collection<Bullet> bullets) {
		enemies.forEach((Entity enemy) -> {
			if(checkEnemyCollision(ship, enemy)) {
				ship.hit(EnumInt.DAMAGE_COLLISION.getValue());
			}
		});
		
		bullets.forEach((Bullet bullet) -> {
			if(checkBulletCollision(ship, bullet)) {
				ship.hit(bullet.getDamage());
			}
		});

	}

}
