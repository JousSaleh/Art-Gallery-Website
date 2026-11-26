// =====================================================================
// File: SceneNavigator.java
// Package: com.artgallery.util
// Role: Switching between FXML screens  -  Requirement R2 (navigation)
// =====================================================================
//
// PURPOSE
// -------
// One place that knows how to load an FXML file and show it in the main
// window. Every controller calls it from an event handler, for example
// inside a button's action event (R2: "each window is opened from an
// event handler").
//
// WHAT TO IMPLEMENT LATER
// -----------------------
// - A static field that stores the main Stage (set once from Main.start).
// - setStage(Stage stage)
// - switchTo(String fxmlFileName)
//     1. Use FXMLLoader to load "/com/artgallery/view/" + fxmlFileName.
//     2. Create a new Scene with the loaded root.
//     3. Add the stylesheet "/com/artgallery/css/style.css" to the scene.
//     4. stage.setScene(scene) and update the title if you want.
//
// ERROR HANDLING (R6)
// -------------------
// - Catch IOException (file not found / FXML error) and show it with
//   AlertHelper.showError("Could not open screen: " + fxmlFileName).
//
// NOTE
// ----
// To pass data between screens (e.g. which artwork was clicked) we use
// UserSession instead of passing objects to the next controller. This is
// simpler for beginners.
// =====================================================================
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