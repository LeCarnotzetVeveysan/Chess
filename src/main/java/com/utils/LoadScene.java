package com.utils;


import com.application.AppData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScene {

    public static void changeLauncherScene(String fxmlToShow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadScene.class.getResource("/fxmls/" + fxmlToShow + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        Stage launcherStage = AppData.getStage(1);
        launcherStage.setTitle("Chess Launcher");
        launcherStage.setScene(scene);
        launcherStage.show();
    }

    public static void changeScene(String fxmlToShow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadScene.class.getResource("/fxmls/" + fxmlToShow + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage mainStage = AppData.getStage(0);
        mainStage.setTitle("LChessV");
        mainStage.setScene(scene);
        mainStage.show();
    }

}
