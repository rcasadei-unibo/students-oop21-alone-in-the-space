module com.aits.aloneinthespacejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
	requires com.almasb.fxgl.core;

    opens com.aits.aloneinthespacejavafx to javafx.fxml;
    exports com.aits.aloneinthespacejavafx;
}