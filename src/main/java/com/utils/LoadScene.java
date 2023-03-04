package com.utils;


import com.application.AppLaunch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScene {

    private static Stage launcherStage;
    private static Stage mainStage;

    public LoadScene(Stage inputLauncher, Stage inputMain){
        launcherStage = inputLauncher;
        mainStage = inputMain;
        launcherStage.setResizable(false);
        mainStage.setResizable(false);
    }

    public static void changeLauncherScene(String fxmlToShow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadScene.class.getResource("fxmls/" + fxmlToShow + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        //Set proper window name
        launcherStage.setTitle("7WA Launcher");
        launcherStage.setScene(scene);
        launcherStage.show();
    }

    public static void changeScene(String fxmlToShow) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoadScene.class.getResource("/fxmls/" + fxmlToShow + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        mainStage.setTitle("LChessV");
        mainStage.setScene(scene);
        mainStage.show();
    }

}
