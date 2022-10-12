package controller;

import javafx.scene.shape.Rectangle;
import model.Entity;

public class CollisionImpl implements Collision {

	@Override
	public boolean checkEnemiesCollision(Entity e, Rectangle r) {
		if(e.getImg().getBoundsInParent().intersects(r.getBoundsInParent())) {
			System.out.println("Collision");
			return true;
		}
		return false;
	}

	@Override
	public boolean checkBulletsCollision(Entity ship, Entity bullet) {
		if(ship.getImg().getBoundsInParent().intersects(bullet.getImg().getBoundsInParent())) {
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
