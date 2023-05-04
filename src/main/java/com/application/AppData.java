package com.application;

import javafx.stage.Stage;

public class AppData {

    public static Stage mainStage, auxiliaryStage;

    public static void setStages(){
        mainStage = new Stage();
        auxiliaryStage = new Stage();
        mainStage.setResizable(false);
        auxiliaryStage.setResizable(false);
    }
    public static Stage getStage(int index){
        if(index == 0){
            return mainStage;
        } else if(index == 1){
            return auxiliaryStage;
        } else{
            return auxiliaryStage;
        }
    }



}
