package controller.collisionDetection;

import model.Bullet;
import model.Entity;
import model.Ship;
import utilities.EnumInt;
import view.GameMap;
import view.hud.HUDImpl;

import java.util.Collection;

/**
 * 
 * Class that implements Collision interface.
 *
 */
public class CollisionImpl implements Collision {

    private GameMap gameMap;
    private HUDImpl hudImpl;

    public CollisionImpl(final GameMap gameMap, final HUDImpl hudImpl) {
        this.gameMap = gameMap;
        this.hudImpl = hudImpl;
    }

    @Override
    public boolean checkEnemyCollision(final Ship ship, final Ship enemy) {
        return ship.getNode().getBoundsInParent().intersects(enemy.getNode().getBoundsInParent());
    }

    @Override
    public boolean checkBulletCollision(final Ship ship, final Bullet bullet) {
        return ship.getNode().getBoundsInParent().intersects(bullet.getNode().getBoundsInParent());
    }

    @Override
    public void checkBorderCollision(final Entity ship) {
        // TODO Auto-generated method stub
    }

    @Override
    public void checkAllCollision(final Ship ship, final Collection<Ship> enemies, final Collection<Bullet> playerBullets, final Collection<Bullet> enemiesBullets) {

        enemies.forEach((Ship enemy) -> {
            if (checkEnemyCollision(ship, enemy)) {
                ship.hit(EnumInt.DAMAGE_COLLISION.getValue());
                enemy.hit(EnumInt.DAMAGE_COLLISION.getValue());
                this.hudImpl.getLifeImpl().lifeDown(EnumInt.DAMAGE_COLLISION.getValue());
            }
        });

        playerBullets.forEach((Bullet bullet) -> {
            enemies.forEach((Ship enemy) -> {
                if (bullet.isAlive() && checkBulletCollision(enemy, bullet)) {
                    enemy.hit(bullet.getDamage());
                    bullet.destroy();
                    this.gameMap.getStatus().setPoints();
                    this.hudImpl.getPointsImpl().setPoints(this.gameMap.getStatus().getPoints());
                }
            });
        });

        enemiesBullets.forEach((Bullet bullet) -> {
            if (bullet.isAlive() && checkBulletCollision(ship, bullet)) {
                ship.hit(bullet.getDamage());
                bullet.destroy();
                this.hudImpl.getLifeImpl().lifeDown(bullet.getDamage());
            }
        });

        enemies.removeIf(e -> !(e.isAlive()));
        enemiesBullets.removeIf(e -> !(e.isAlive()));
        playerBullets.removeIf(e -> !(e.isAlive()));
    }

}
