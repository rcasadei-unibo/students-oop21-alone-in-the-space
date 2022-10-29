package controller.collisionDetection;

import model.Entity;
import model.bullet.Bullet;
import model.ship.Ship;
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
    public void checkAllCollision(final Ship player, final Collection<Ship> enemies, final Collection<Bullet> playerBullets, final Collection<Bullet> enemiesBullets) {

        enemies.forEach((Ship enemy) -> {
            if (enemy.isAlive() && checkEnemyCollision(player, enemy)) {
        	player.hit(EnumInt.DAMAGE_COLLISION.getValue());
                //enemy.hit(EnumInt.DAMAGE_COLLISION.getValue());
                this.hudImpl.getLifeImpl().lifeDown(EnumInt.DAMAGE_COLLISION.getValue());

            }
        });

        playerBullets.forEach((Bullet bullet) -> enemies.forEach((Ship enemy) -> {
            if (bullet.isAlive() && checkBulletCollision(enemy, bullet)) {
                enemy.hit(bullet.getDamage());
                bullet.destroy();
                if(!enemy.isAlive()) {
                    this.gameMap.getStatus().setPoints(this.gameMap.getStatus().getPoints() + EnumInt.ONE.getValue());
                    this.hudImpl.getPointsImpl().setPoints(this.gameMap.getStatus().getPoints());
                }
            }
        }));

        enemiesBullets.forEach((Bullet bullet) -> {
            if (bullet.isAlive() && checkBulletCollision(player, bullet)) {
                player.hit(bullet.getDamage());
                bullet.destroy();
                this.gameMap.getStatus().setLifePoints(this.gameMap.getStatus().getLifePoints()- bullet.getDamage());
                this.hudImpl.getLifeImpl().lifeDown(bullet.getDamage());
            }
        });



        
    }

}
