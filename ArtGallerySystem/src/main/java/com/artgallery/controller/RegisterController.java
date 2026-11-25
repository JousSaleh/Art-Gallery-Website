package com.artgallery.controller;

import com.artgallery.model.User;
import com.artgallery.service.UserService;
import com.artgallery.util.InputValidator;
import com.artgallery.util.SceneNavigator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {

    @FXML private TextField fullNameField;
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private CheckBox termsCheckBox;
    @FXML private Button registerButton;
    @FXML private Button backButton;
    @FXML private Label messageLabel;

    private final UserService userService = new UserService();

    @FXML
    public void initialize() {

        registerButton.disableProperty().bind(
                termsCheckBox.selectedProperty().not()
        );
    }

    @FXML
    private void handleRegister() {

        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        messageLabel.setStyle("-fx-text-fill: red;");

        if (InputValidator.isEmpty(fullName)
                || InputValidator.isEmpty(username)
                || InputValidator.isEmpty(email)
                || InputValidator.isEmpty(password)
                || InputValidator.isEmpty(confirmPassword)) {

            messageLabel.setText("Please fill in all fields.");
            return;
        }

        if (!InputValidator.isSafeForStorage(fullName)) {
            messageLabel.setText("Full name contains invalid characters.");
            return;
        }

        if (!InputValidator.isValidUsername(username)) {
            messageLabel.setText(
                    "Username must contain at least 3 characters "
                    + "without spaces or invalid characters."
            );
            return;
        }

        if (userService.usernameExists(username)) {
            messageLabel.setText("Username already exists.");
            return;
        }

        if (!InputValidator.isValidEmail(email)) {
            messageLabel.setText("Please enter a valid email.");
            return;
        }

        if (!InputValidator.isValidPassword(password)) {
            messageLabel.setText(
                    "Password must contain at least 6 characters "
                    + "without invalid characters."
            );
            return;
        }

        if (!InputValidator.passwordsMatch(password, confirmPassword)) {
            messageLabel.setText("Passwords do not match.");
            return;
        }

        User newUser = new User(
                username, password, fullName, email
        );

        try {

            if (!userService.register(newUser)) {
                messageLabel.setText("Could not create account.");
                return;
            }

        } catch (IllegalStateException e) {

            messageLabel.setText("Could not save account. Please try again.");
            return;
        }

        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Account created successfully.");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText("Account created successfully.");
        alert.showAndWait();

        SceneNavigator.switchTo("LoginView.fxml");
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("WelcomeView.fxml");
    }
}