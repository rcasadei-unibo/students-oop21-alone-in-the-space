package controller.collisionDetection;

import javafx.scene.shape.Rectangle;
import model.Entity;

public class CollisionImpl implements Collision {

	@Override
	public boolean checkEnemiesCollision(Entity ship, Entity enemy) {
		if(ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent())) {
			System.out.println("Collision");
			return true;
		}
		return false;
	}

	@Override
	public boolean checkBulletsCollision(Entity ship, Entity bullet) {
		if(ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent())) {
			System.out.println("Collision");
			return true;
		}
		return false;
	}

	@Override
	public void checkBorderCollision(Entity ship) {
		// TODO Auto-generated method stub

	}

}
