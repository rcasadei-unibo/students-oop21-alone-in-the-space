module application {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
	requires com.almasb.fxgl.core;

    opens application to javafx.fxml;
    opens controller.gameSwitcher to javafx.fxml;
    exports application;
}
