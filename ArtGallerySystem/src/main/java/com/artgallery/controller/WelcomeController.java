package com.artgallery.controller;

import com.artgallery.util.SceneNavigator;
import com.artgallery.util.AlertHelper;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class WelcomeController {

    @FXML
    private void handleLogin() {
        SceneNavigator.switchTo("LoginView.fxml");
    }

    @FXML
    private void handleRegister() {
        SceneNavigator.switchTo("RegisterView.fxml");
    }

    @FXML
    private void handleExit() {

        boolean confirmed = AlertHelper.confirm(
                "Are you sure you want to exit the Art Gallery?"
        );

        if (confirmed) {
            Platform.exit();
        }
    }
}