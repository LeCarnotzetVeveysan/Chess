package com.application;

import com.utils.LoadScene;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.application.AppData.setStages;
import static com.utils.LoadScene.changeLauncherScene;

public class ChessApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        setStages();
        changeLauncherScene("main-menu");
    }

    public static void main(String[] args) {
        launch();
    }
}
