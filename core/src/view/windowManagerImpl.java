package view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class scewindowManagerImpl implements windowManager{

    private Stage mainStage;

    public WindowManagerImpl(final gameLoop) {
        this.mainStage = gameLoop.getStage();
        this.mainStage.setResizable(false);
    }

    @Override
    public void addGameMap(GameMapImpl gameMap) {
        Scene scene = new Scene(new VBox(gameMap.getGameContainer()));
        gameMap.setScene(scene);
        gameMap.setStageReference(this.getStage());
    }

    @Override
    public Stage getStage() {
        return this.mainStage;
    }
}
