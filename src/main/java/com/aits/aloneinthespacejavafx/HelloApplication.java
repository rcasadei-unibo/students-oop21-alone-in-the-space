package com.aits.aloneinthespacejavafx;

import controller.gameSwitcher.SceneController;
import controller.gameSwitcher.SceneControllerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.EnumInt;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(final Stage windowManager) throws IOException {
        windowManager.setHeight(Double.valueOf(EnumInt.HEIGHT.getValue()));
        windowManager.setWidth(Double.valueOf(EnumInt.WIDTH.getValue()));
        windowManager.setTitle("Alone in the space");
        windowManager.setResizable(false);
        windowManager.getIcons().add(new Image("file:icon16.png"));
        windowManager.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        SceneController sc = new SceneControllerImpl(windowManager);
        sc.switchToMainMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}