module com.aits.aloneinthespacejavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.aits.aloneinthespacejavafx to javafx.fxml;
    exports com.aits.aloneinthespacejavafx;
}