package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.almasb.fxgl.core.math.Vec2;

import model.bullet.Bullet;
import model.bullet.BulletFactory;
import model.ship.EnemyFactory;
import model.ship.Ship;

class enemyTest {

    @Test
    void shotTest() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(0,0));
	Ship enemy2 = EnemyFactory.basicEnemy(new Vec2(100,100));
	enemy.setTarget(enemy2);
	Bullet bullet = enemy.shot();
	assertTrue(enemy.getPosition().equals(bullet.getPosition()));
	assertTrue(enemy.getDirection().equals(bullet.getDirection()));
    }
    
    @Test
    void shotTest2() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(10,10));
	Ship enemy2 = EnemyFactory.basicEnemy(new Vec2(100,100));
	enemy.setTarget(enemy2);
	Bullet bullet = enemy.shot();
	assertTrue(enemy.getPosition().equals(bullet.getPosition()));
	assertTrue(enemy.getDirection().equals(bullet.getDirection()));
    }
    
    @Test
    void shotTest3() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(10,10));
	Ship enemy2 = EnemyFactory.basicEnemy(new Vec2(100,100));
	enemy.setTarget(enemy2);
	enemy.move(100000L);
	Bullet bullet = enemy.shot();
	assertTrue(enemy.getPosition().equals(bullet.getPosition()));
	assertTrue(enemy.getDirection().equals(bullet.getDirection()));
    }
    
    @Test
    void move() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(10,10));
	var posInit = enemy.getPosition();
	enemy.move(100000L);
	assertFalse(posInit.equals(enemy.getPosition()));
    }
    
    @Test
    void checkShoot() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(0,0));
	Ship enemy2 = EnemyFactory.basicEnemy(enemy.getDirection());
	enemy.setTarget(enemy2);
	assertTrue(enemy.isInRangeOfAttack(100000L));
    }
    
    @Test
    void multipleShoots() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(0,0));
	Ship enemy2 = EnemyFactory.basicEnemy(enemy.getDirection());
	enemy.setTarget(enemy2);
	assertTrue(enemy.isInRangeOfAttack(0));
	enemy.shot();
	assertFalse(enemy.isInRangeOfAttack(0));
    }
    
    @Test
    void testHealth() {
	Ship enemy = EnemyFactory.basicEnemy(new Vec2(0,0));
	Bullet bullet = BulletFactory.BasicBullet(new Vec2(0,0), new Vec2(0,0));
	int health = enemy.getHealth();
	enemy.strike(bullet.getDamage());	
	assertTrue(health == enemy.getHealth()+bullet.getDamage());
    }

}
