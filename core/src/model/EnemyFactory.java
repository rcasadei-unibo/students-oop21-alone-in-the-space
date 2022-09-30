package model;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class EnemyFactory {
	private static final String assetFolder="";
	public static Ship BasicEnemy() {
		class BasicEnemy extends AbstractShip{

			public BasicEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed) {
				super(health, maxSpeed, acceleration, rotationSpeed);
				// TODO Auto-generated constructor stub
			}	
			
		};
		Ship en = new BasicEnemy(0, 0, 0, 0);
		en.setGun(GunFactory.missile());
		en.setSprite(loadImage(""));//error fetching image
		return en;	
	}
	public static Ship MissileEnemy() {
		Ship en= new AbstractShip(100,10,2,60);
		en.setSprite();
		return en;	
	}
	public static Ship RifleEnemy() {
		Ship en= new AbstractShip(100,10,2,60);
		en.setSprite(null);
		return en;
	}
	private static Image loadImage(String pathName) {
		Image img;
		try {
			img = new Image(""+assetFolder+pathName);
		} catch (Exception e) {
			throw e;
		}
		return img;
	}
}
