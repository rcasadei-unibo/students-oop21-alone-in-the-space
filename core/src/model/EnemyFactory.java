package model;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class EnemyFactory {
	public static Ship BasicEnemy() throws IllegalArgumentException{
		class BasicEnemy extends AbstractShip{

			public BasicEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed) {
				super(health, maxSpeed, acceleration, rotationSpeed);
				// TODO Auto-generated constructor stub
			}	
			
		};
		Ship en = new BasicEnemy(0, 0, 0, 0);
		en.setGun(GunFactory.missile());
		en.setSprite(loadImage("ship_0.png"));
		return en;	
	}
	public static Ship MissileEnemy() throws IllegalArgumentException{
		class MissileEnemy extends AbstractShip{

			public MissileEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed) {
				super(health, maxSpeed, acceleration, rotationSpeed);
				// TODO Auto-generated constructor stub
			}
			
		};
		Ship en = new MissileEnemy(0, 0, 0, 0);
		en.setGun(GunFactory.missile());
		en.setSprite(loadImage("ship_30.png"));
		return en;	
	}
	public static Ship RifleEnemy() throws IllegalArgumentException{
		class RifleEnemy extends AbstractShip{

			public RifleEnemy(float health, float maxSpeed, float acceleration, float rotationSpeed) {
				super(health, maxSpeed, acceleration, rotationSpeed);
				// TODO Auto-generated constructor stub
			}	
			
		};
		Ship en = new RifleEnemy(0, 0, 0, 0);
		en.setGun(GunFactory.rifle());
		en.setSprite(loadImage("ship_22.png"));
		return en;	
	}
	private static Image loadImage(String name) throws IllegalArgumentException{
		Image img;
		
		try {
			img = new Image("file:"+name);
		} catch (Exception e) {
			throw e;
		}
		return img;
	}
}
