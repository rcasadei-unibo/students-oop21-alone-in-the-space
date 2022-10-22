package application;

import controller.gameSwitcher.SceneController;
import controller.gameSwitcher.SceneControllerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utilities.EnumInt;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class Main extends Application {
	public static void main (String[] arg) {
		/*Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		new Lwjgl3Application(new AloneInTheSpace(), config);*/
		launch(arg);
	}

	@Override
	public void start(final Stage windowManager) throws Exception {
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
}
