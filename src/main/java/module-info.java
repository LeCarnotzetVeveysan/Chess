module com {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.application to javafx.fxml;
    exports com.application;
    opens com.chess to javafx.fxml;
    exports com.chess;
    opens com.controllers to javafx.fxml;
    exports com.controllers;
    opens com.utils to javafx.fxml;
    exports com.utils;
    opens com.tests to javafx.fxml;
    exports com.tests;

}