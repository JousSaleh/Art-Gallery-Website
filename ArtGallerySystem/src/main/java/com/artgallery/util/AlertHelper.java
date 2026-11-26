// =====================================================================
// File: AlertHelper.java
// Package: com.artgallery.util
// Role: Showing messages to the user / error handling  -  Requirement R6
// =====================================================================
//
// PURPOSE
// -------
// Small helper that shows JavaFX Alert dialogs so every screen shows
// errors and success messages in the same, clear way.
//
// METHODS TO IMPLEMENT LATER (static)
// -----------------------------------
// - showError(String message)
//     Alert of type ERROR, title "Error".
// - showSuccess(String message)  (or showInfo)
//     Alert of type INFORMATION, title "Success".
// - showWarning(String message)
//     Alert of type WARNING.
// - confirm(String message)  -> boolean
//     Alert of type CONFIRMATION; return true if the user pressed OK.
//     (Use it for "Are you sure you want to log out / exit?")
//
// WHEN TO USE ALERTS vs LABELS
// ----------------------------
// - Small form mistakes (empty field)   -> a red message Label on the screen.
// - Important results (account created) -> showSuccess(...)
// - Unexpected problems (file error)    -> showError(...)
//
// RULE
// ----
// Every try / catch in controllers should end with a friendly message,
// never with a crash or a raw stack trace shown to the user.
// =====================================================================
package com.artgallery.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public final class AlertHelper {

    private AlertHelper() {
        // Utility class: no objects needed
    }

    public static void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    public static void showSuccess(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Success", message);
    }

    public static void showWarning(String message) {
        showAlert(Alert.AlertType.WARNING, "Warning", message);
    }

    public static boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        return alert.showAndWait()
                .orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    private static void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}