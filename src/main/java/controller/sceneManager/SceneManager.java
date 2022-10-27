package controller.sceneManager;

import com.almasb.fxgl.core.math.Vec2;
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
     */
    public void update(long deltaTime) {
        //this.updateBackground();
        //TODO soundmanager
        this.entities.forEach(entity -> updateEntityPosition(entity, deltaTime));
    }

    /**
     * Entity position update.
     * @param entity
     * @param deltaTime
     */
    private void updateEntityPosition(final Entity entity, long deltaTime) {
        entity.move(deltaTime);
        Vec2 position = entity.getPosition();
        entity.getNode().relocate(position.x, position.y);
    }

    /**
     * Background update.
     */
    /*private void updateBackground() {
        for (Node image : this.gameMap.getBackground()) {
            image.setLayoutY(image.getLayoutY() + EnumInt.FOUR.getValue());
            if (image.getLayoutY() >= this.gameMap.getHeight().intValue()) {
                image.setLayoutY(-this.gameMap.getHeight().intValue());
            }
        }
    }*/


}
