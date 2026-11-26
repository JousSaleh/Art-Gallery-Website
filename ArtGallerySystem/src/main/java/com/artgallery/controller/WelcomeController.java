// =====================================================================
// File: WelcomeController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R2, R3
// Paired view: resources/com/artgallery/view/WelcomeView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Controls the first screen. It introduces the Art Gallery System and
// lets the user go to Login or Registration.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in WelcomeView.fxml)
// ---------------------------------------------------------------
// - loginButton    : Button
// - registerButton : Button
// - exitButton     : Button
// - logoImageView  : ImageView  (optional, gallery logo from images/app/)
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Action event: loginButton    -> go to LoginView.fxml
// - Action event: registerButton -> go to RegisterView.fxml
// - Action event: exitButton     -> close the application
//   (Platform.exit()), optionally after a confirmation Alert.
//
// TIP (R5): you can register these handlers inside initialize() with
// lambda expressions, e.g. loginButton.setOnAction(e -> ...),
// OR use onAction="#handleLogin" in the FXML. Pick ONE style and use it
// consistently in the whole project (no duplicated handlers).
//
// INPUT VALIDATION
// ----------------
// None on this screen (there are no input fields).
//
// NAVIGATION
// ----------
// Uses SceneNavigator.switchTo("LoginView.fxml") /
// SceneNavigator.switchTo("RegisterView.fxml").
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// None. This screen does not read or change any data.
// =====================================================================
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