package com.application;

import javafx.application.Application;
import javafx.stage.Stage;
import com.utils.LoadScene;

import static com.utils.LoadScene.changeScene;

public class AppLaunch extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage mainStage = new Stage();
        Stage seconStage = new Stage();
        LoadScene ls = new LoadScene(mainStage, seconStage);
        ls.changeScene("testBoard");
    }

    public static void main(String[] args) {
        launch();
    }
}