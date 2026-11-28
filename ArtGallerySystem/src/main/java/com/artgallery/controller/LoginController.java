package com.artgallery.controller;

import com.artgallery.model.User;
import com.artgallery.service.UserService;
import com.artgallery.util.InputValidator;
import com.artgallery.util.UserSession;
import com.artgallery.util.SceneNavigator;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button backButton;
    @FXML private Hyperlink registerLink;
    @FXML private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    public void initialize() {

        loginButton.disableProperty().bind(
                usernameField.textProperty().isEmpty()
                        .or(passwordField.textProperty().isEmpty())
        );

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER
                    && !loginButton.isDisabled()) {
                handleLogin();
            }
        });
    }

    @FXML
    private void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (InputValidator.isEmpty(username)
                || InputValidator.isEmpty(password)) {

            messageLabel.setText(
                    "Please enter your username and password."
            );
            return;
        }

        Optional<User> user = userService.login(username, password);

        if (user.isPresent()) {

            UserSession.setCurrentUser(user.get());
            SceneNavigator.switchTo("GalleryView.fxml");

        } else {

            messageLabel.setText("Invalid username or password.");
        }
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("WelcomeView.fxml");
    }

    @FXML
    private void handleRegister() {
        SceneNavigator.switchTo("RegisterView.fxml");
    }
}