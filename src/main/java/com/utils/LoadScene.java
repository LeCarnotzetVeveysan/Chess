package com.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.application.AppData.getStage;

public class LoadScene {

    public static void changeLauncherScene(String fxmlToShow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadScene.class.getResource("/fxmls/" + fxmlToShow + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage launcherStage = getStage(1);
        launcherStage.setTitle("Chess Launcher");
        launcherStage.setScene(scene);
        launcherStage.show();
    }

    public static void changeScene(String fxmlToShow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadScene.class.getResource("/fxmls/" + fxmlToShow + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage mainStage = getStage(0);
        mainStage.setTitle("LChessV");
        mainStage.setScene(scene);
        mainStage.show();
        hideStage(1);
    }

    public static void hideStage(int index){
        Stage stage = getStage(index);
        stage.hide();

    }

}
