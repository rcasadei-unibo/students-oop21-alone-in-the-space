package view;

import javafx.stage.Stage;

public interface windowManager {

    /**
     * Add the gamemap to the stage and set its scene.
     *
     * @param gameMap
     */

    void addGameMap(GameMapImpl gameMap);
    /**
     * @return actual Stage.
     */
    Stage getStage();
}
