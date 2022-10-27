package controller.collisionDetection;

import java.util.Collection;

import model.Bullet;
import model.Entity;
import model.Ship;

/**
 * 
 * This interface detects collision between ships and bullets. 
 *
 */
public interface Collision {

    /**
     * Check if there is a collision with enemies.
     * @param ship the player ship
     * @param enemy the enemy ship
     * @return True if a collision occurred
     */
    boolean checkEnemyCollision(Ship ship, Ship enemy);

    /**
     * Check if there is a collision with bullets.
     * @param ship the player ship
     * @param bullet the active bullet
     * @return True if a collision occurred
     */
    boolean checkBulletCollision(Ship ship, Bullet bullet);

    /**
     * Check collision with enemies and bullets.
     * @param ship
     * @param enemies
     * @param playerBullets
     * @param enemiesBullets
     */
    void checkAllCollision(Ship ship, Collection<Ship> enemies, Collection<Bullet> playerBullets, Collection<Bullet> enemiesBullets);

    /**
     * Check if there is a collision with borders.
     * @param ship the player ship
     */
    void checkBorderCollision(Entity ship);

}
