package com.application;

import com.utils.LoadScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChessApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage mainStage = new Stage();
        Stage seconStage = new Stage();
        LoadScene ls = new LoadScene(mainStage, seconStage);
        ls.changeLauncherScene("main-menu");
    }

    public static void main(String[] args) {
        launch();
    }
}
