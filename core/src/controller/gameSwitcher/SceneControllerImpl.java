package controller.gameSwitcher;

import model.GameEngine;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneControllerImpl implements SceneController{

    private Stage windowManager;
    private GameEngine gameEngine;
    private InputController inputController;
    private Ranking ranking;

    public SceneControllerImpl(final Stage windowManager) throws IOException{
        this.windowManager = windowManager;
        this.ranking = new RankingImpl();
        this.inputController = new InputControllerImpl(new Scene(new Group()));
        this.windowManager.show();
        //soundManager
    }

    @Override
    public void switchToMainMenu() throws IOException {

    }

    @Override
    public void switchToGame(String name) throws IOException {

    }

    @Override
    public void switchToScores() throws IOException {

    }

    @Override
    public void switchToNickname() throws IOException {

    }

    @Override
    public void switchToEndMenu(int scores) throws IOException {

    }

    @Override
    public void switchToControls() throws IOException {

    }

    @Override
    public void quit() {

    }

    @Override
    public Stage getStage() {
        return this.windowManager;
    }

    @Override
    public Ranking getRanking() {
        return null;
    }

    @Override
    public InputController getInputController() {
        return null;
    }
}
