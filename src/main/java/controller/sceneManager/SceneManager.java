package controller.sceneManager;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;
import model.Entity;
import view.GameMap;

import java.util.Set;

public class SceneManager {

    private GameMap gameMap;
    private Set<Entity> entities;

    /**
     * Constructor.
     *
     * @param gameMap
     */
    public SceneManager(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.entities = this.gameMap.getActiveEntities();
    }

    /**
     * Game update.
     * 
     * @param deltaTime
     */
    public void update(final long deltaTime) {
        // this.updateBackground();
        // TODO soundmanager
        this.entities.forEach(entity -> updateEntityPosition(entity, deltaTime));
    }

    /**
     * Entity position update.
     * 
     * @param entity
     * @param deltaTime
     */
    private void updateEntityPosition(final Entity entity, final long deltaTime) {
        if (entity.isAlive()) {
            entity.move(deltaTime);
            final Vec2 position = entity.getPosition();
            ImageView image = (ImageView) entity.getNode();
            image.getImage().getHeight();
            entity.getNode().relocate(position.x - image.getImage().getWidth() / 2,
                    position.y - image.getImage().getHeight() / 2);
            entity.getNode().setRotate(entity.getAngle());
        } else {
            try {
                this.gameMap.removeEntity(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Background update.
     */
    /*
     * private void updateBackground() { for (final Node image :
     * this.gameMap.getBackground()) { image.setLayoutY(image.getLayoutY() +
     * EnumInt.FOUR.getValue()); if (image.getLayoutY() >=
     * this.gameMap.getHeight().intValue()) {
     * image.setLayoutY(-this.gameMap.getHeight().intValue()); } } }
     */

}
