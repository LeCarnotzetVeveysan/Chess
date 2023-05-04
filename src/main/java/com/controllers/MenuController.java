package com.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

import static com.utils.LoadScene.changeScene;

public class MenuController {
    public void onStartButtonClicked(ActionEvent actionEvent) throws IOException {
        changeScene("game-board");
    }
}
