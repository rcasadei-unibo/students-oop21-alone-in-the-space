package controller.collisionDetection;

import model.Entity;

public class CollisionImpl implements Collision {

	@Override
	public boolean checkEnemiesCollision(Entity ship, Entity enemy) {
		return ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent());
	}

	@Override
	public boolean checkBulletsCollision(Entity ship, Entity bullet) {
		return ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent());
	}

	@Override
	public void checkBorderCollision(Entity ship) {
		// TODO Auto-generated method stub

	}

}
