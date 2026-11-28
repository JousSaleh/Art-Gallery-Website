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