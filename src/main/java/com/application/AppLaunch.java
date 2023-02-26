package com.application;

import javafx.application.Application;
import javafx.stage.Stage;
import com.utils.LoadScene;

public class AppLaunch extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Stage launcherStage = new Stage();
        //LoadScene ls = new LoadScene(launcherStage, mainStage, selectionStage);
        //ls.changeLauncherScene("app-launcher");
    }

    public static void main(String[] args) {
        launch();
    }
}