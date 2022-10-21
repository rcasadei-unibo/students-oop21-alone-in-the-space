package controller.gameSwitcher;

import javafx.stage.Stage;

import java.io.IOException;

public abstract class BasicFXMLController {
    private Stage windowManager;
    private SceneController sceneController;

    /**
     * Constructor.
     * @param sceneController
     * @throws IOException
     */
    public BasicFXMLController(final SceneController sceneController) throws IOException {
        this.windowManager = sceneController.getStage();
        this.sceneController = sceneController;
        //soundManager
    }

    //public void buttonPressedSound(){}

    /**
     * @return windowManager reference.
     */
    public Stage getWindowManager() {
        return this.windowManager;
    }

    //public SoundManager getSoundManager() {}

    /**
     *
     * @return sceneController reference.
     */
    public SceneController getSceneController() {
        return this.sceneController;
    }
}
