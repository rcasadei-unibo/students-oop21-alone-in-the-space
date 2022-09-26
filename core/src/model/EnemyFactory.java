package model;


public class EnemyFactory {
	public static Enemy BasicEnemy() {
		Enemy en= new AbstractShip(100,10,2,60);
		en.setSprite(null);//error fetching image
		return en;	
	}
	public static Enemy MissileEnemy() {
		Enemy en= new AbstractShip(100,10,2,60);
		en.setSprite(null);
		return en;	
	}
	public static Enemy RifleEnemy() {
		Enemy en= new AbstractShip(100,10,2,60);
		en.setSprite(null);
		return en;
	}
}
