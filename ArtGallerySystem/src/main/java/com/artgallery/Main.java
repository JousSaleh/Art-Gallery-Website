
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