package model;

import java.util.List;

import com.almasb.fxgl.core.math.Vec2;



abstract class GunImpl implements Gun {
   private int degRange;
   private Ship actualShip;
	public GunImpl(int degRange,Ship ship) {
		this.degRange= degRange;
		this.actualShip=ship;
	}
	
	public Bullet shot(Vec2 direction) {
		// TODO Auto-generated method stub
		return BulletFactory.BasicBullet(actualShip.getPosition(),actualShip.getDirection());
		 
	}

	public Boolean isInRange(Vec2 shipPos, Vec2 direction, List<Vec2> enemyPos) {
		// TODO Auto-generated method stub
		return null;
	}

	public float getDegRange() {
		// TODO Auto-generated method stub
		return this.degRange;
	}
	
}
public class GunFactory {

	public static Gun rifle(Ship spaceship) {
		// TODO Auto-generated method stub
		class rifle extends GunImpl{

			public rifle(int degRange, Ship ship) {
				super(degRange, ship);
				// TODO Auto-generated constructor stub
			}
			
		};
		return new rifle(10,spaceship);
	}

	public static Gun missile(Ship spaceship) {
		// TODO Auto-generated method stub
		class missile extends GunImpl{

			public missile(int degRange, Ship ship) {
				super(degRange, ship);
				// TODO Auto-generated constructor stub
			}
			
		}return new missile(45,spaceship);
	
	}

	public static Gun shootgun(Ship spaceship) {
		// TODO Auto-generated method stub
		class shootgun extends GunImpl{

			public shootgun(int degRange, Ship ship) {
				super(degRange, ship);
				// TODO Auto-generated constructor stub
			}
			
		}return new shootgun(30,spaceship);
	}

}
