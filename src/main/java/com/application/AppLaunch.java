package com.application;

import javafx.application.Application;
import javafx.stage.Stage;
import com.utils.LoadScene;

import static com.application.AppData.setStages;
import static com.utils.LoadScene.changeScene;

public class AppLaunch extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        setStages();
        changeScene("game-board");
    }

    public static void main(String[] args) {
        launch();
    }
}