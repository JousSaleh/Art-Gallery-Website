package com.artgallery.util;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SceneNavigator {

    private static Stage primaryStage;

    private SceneNavigator() {
        // Utility class: no objects needed
    }

    // Store the application's main window
    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    // Switch to another FXML screen
    public static void switchTo(String fxmlFileName) {

        if (primaryStage == null) {
            AlertHelper.showError(
                    "Application window is not ready."
            );
            return;
        }

        String fxmlPath =
                "/com/artgallery/view/" + fxmlFileName;

        URL fxmlURL =
                SceneNavigator.class.getResource(fxmlPath);

        if (fxmlURL == null) {
            AlertHelper.showError(
                    "Could not find screen: " + fxmlFileName
            );
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(fxmlURL);

            Parent root = loader.load();

            Scene scene = new Scene(root);

            // Apply the shared stylesheet
            URL cssURL = SceneNavigator.class.getResource(
                    "/com/artgallery/css/style.css"
            );

            if (cssURL != null) {
                scene.getStylesheets().add(
                        cssURL.toExternalForm()
                );
            }

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException | RuntimeException e) {

            AlertHelper.showError(
                    "Could not open screen: " + fxmlFileName
            );

            e.printStackTrace();
        }
    }
}