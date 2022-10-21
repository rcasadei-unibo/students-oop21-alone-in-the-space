package com.aits.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

//import core.src.controller.sceneManager.AloneInTheSpace;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher extends Application {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Alone in the space");
		config.setWindowIcon("assets/icon.png");
		//new Lwjgl3Application(new AloneInTheSpace(), config);
	}

	@Override
	public void start(final Stage windowManager) throws Exception {
		windowManager.setHeight(Double.valueOf());
		windowManager.setWidth();
		windowManager.setTitle("Alone in the space");
		windowManager.setResizable(false);
		windowManager.getIcons().add(new Image("file:icon16.png"));
		windowManager.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}
}
