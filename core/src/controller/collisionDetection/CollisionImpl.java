package controller.collisionDetection;

import java.util.Collection;

import model.Entity;

public class CollisionImpl implements Collision {

	@Override
	public boolean checkEnemyCollision(Entity ship, Entity enemy) {
		return ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent());
	}

	@Override
	public boolean checkBulletCollision(Entity ship, Entity bullet) {
		return ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent());
	}

	@Override
	public void checkBorderCollision(Entity ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkAllCollision(Entity ship, Collection<Entity> enemies, Collection<Entity> bullets) {
		enemies.forEach((Entity enemy) -> {
			if(checkEnemyCollision(ship, enemy)) {
				//TO DEVELOP
			}
		});
		
		bullets.forEach((Entity bullet) -> {
			if(checkBulletCollision(ship, bullet)) {
				//TO DEVELOP
			}
		});

	}

}
