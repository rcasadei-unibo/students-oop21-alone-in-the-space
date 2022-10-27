package controller.gameSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class ScoresController extends BasicFXMLController {

    @FXML
    private TextFlow scoreText;

    /**
     * Constructor.
     *
     * @param sceneController
     * @throws IOException
     */
    public ScoresController(SceneController sceneController) throws IOException {
        super(sceneController);
    }

    @FXML
    private void initialize() {
        this.setUpScoresScene();
    }

    private void setUpScoresScene() {
        this.refreshScoresData();
        this.scoreText.setVisible(true);
    }

    public void refreshScoresData() {
        final Text rankText = new Text(this.getSceneController().getRanking().getFormattedRanking(5));
        rankText.setFill(Color.BLUE);
        //rankText.setFont();
        this.scoreText.getChildren().add(rankText);
    }

    @FXML
    void showMainMenu(final ActionEvent event) throws IOException {
        //super.buttonPressedSound();
        super.getSceneController().switchToMainMenu();
    }
}
