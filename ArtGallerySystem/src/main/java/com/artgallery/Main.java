// =====================================================================
// File: Main.java
// Package: com.artgallery
// Role: Application entry point (the "main Java file" step in Lecture 9)
// =====================================================================
//
// PURPOSE
// -------
// Starts the JavaFX application and shows the first window.
//
// WHAT TO WRITE LATER  (see Lecture 6: Application, Stage, Scene)
// ------------------------------------------------------------
// 1. Make this class extend javafx.application.Application.
//
// 2. Override start(Stage primaryStage):
//    a) Load ALL saved data from the data/ folder by calling the
//       repositories (through the services):
//         - artworks.txt, users.txt, comments.txt, likes.txt, ratings.txt
//       (Requirement R4: data is loaded when the application starts.)
//    b) Give the primaryStage to SceneNavigator so every controller can
//       switch scenes later.
//    c) Load "WelcomeView.fxml" with FXMLLoader, put it in a Scene,
//       add "style.css" to the scene, set the stage title
//       ("Art Gallery System") and call primaryStage.show().
//    d) Surround the loading with try / catch (IOException) and show an
//       error with AlertHelper if an FXML file cannot be found (R6).
//
// 3. (Optional) Override stop() to make sure data is saved before the
//    program closes. Data should already be saved after every change,
//    so this is only an extra safety step.
//
// 4. Write the standard main(String[] args) method that calls launch(args).
//
// NOTE
// ----
// Keep this class short. No business logic here.
// =====================================================================


package com.artgallery;

import com.artgallery.service.ArtworkService;
import com.artgallery.service.CommentService;
import com.artgallery.service.UserService;
import com.artgallery.service.LikeService;
import com.artgallery.service.RatingService;
import com.artgallery.util.SceneNavigator;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    // Shared services
    private static UserService userService;
    private static LikeService likeService;
    private static RatingService ratingService;

    @Override
    public void start(Stage stage) {

        // Load all saved data when the application starts (R4)
        ArtworkService.loadArtworks();
        CommentService.loadComments();

        // UserService automatically loads users in its constructor
        userService = new UserService();

        likeService = new LikeService();
        likeService.loadLikes();

        ratingService = new RatingService();
        ratingService.loadRatings();

        // Set up navigation
        SceneNavigator.setStage(stage);

        // Open the Welcome screen
        stage.setTitle("Art Gallery System");
        SceneNavigator.switchTo("WelcomeView.fxml");
        stage.show();
    }

    // Allow other classes to access the shared services
    public static UserService getUserService() {
        return userService;
    }

    public static LikeService getLikeService() {
        return likeService;
    }

    public static RatingService getRatingService() {
        return ratingService;
    }

    public static void main(String[] args) {
        launch(args);
    }
}