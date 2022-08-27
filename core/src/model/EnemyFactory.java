package model;


public class EnemyFactory {
	public static Ship BasicEnemy() {
		return new BasicShip(100,10,2,60);	
	}
}
