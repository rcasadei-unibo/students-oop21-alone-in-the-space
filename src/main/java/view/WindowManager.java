package view;

import javafx.stage.Stage;

/**
*
*/
public interface WindowManager {

    /**
     * Add the gamemap to the stage and set its scene.
     *
     * @param gameMap
     */

    void addGameMap(GameMap gameMap);

    /**
     * @return actual Stage.
     */
    Stage getStage();
}
