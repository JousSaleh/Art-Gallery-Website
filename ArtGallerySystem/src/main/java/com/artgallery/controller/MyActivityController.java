package com.artgallery.controller;

import com.artgallery.model.Artwork;
import com.artgallery.model.Comment;
import com.artgallery.model.Rating;
import com.artgallery.model.User;

import com.artgallery.service.ArtworkService;
import com.artgallery.service.CommentService;
import com.artgallery.service.LikeService;
import com.artgallery.service.RatingService;

import com.artgallery.util.UserSession;
import com.artgallery.util.SceneNavigator;

import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

public class MyActivityController {

    @FXML private Label userLabel;
    @FXML private ListView<Comment> myCommentsListView;
    @FXML private ListView<Artwork> likedListView;
    @FXML private ListView<String> ratingsListView;
    @FXML private Label summaryLabel;
    @FXML private Button backButton;

    private final LikeService likeService = new LikeService();
    private final RatingService ratingService = new RatingService();

    @FXML
    public void initialize() {

        myCommentsListView.setPlaceholder(
                new Label("You have not commented yet.")
        );

        likedListView.setPlaceholder(
                new Label("You have not liked any artwork yet.")
        );

        ratingsListView.setPlaceholder(
                new Label("You have not rated any artwork yet.")
        );

        User currentUser = UserSession.getCurrentUser();

        if (currentUser == null) {
            userLabel.setText("No user logged in.");
            summaryLabel.setText("Please log in first.");
            return;
        }

        String username = currentUser.getUsername();

        userLabel.setText("Activity of: " + username);

        CommentService.loadComments();
        likeService.loadLikes();
        ratingService.loadRatings();

        List<Comment> comments =
                CommentService.getCommentsByUser(username);

        List<Artwork> likedArtworks =
                likeService.getLikedArtworks(username);

        List<Rating> ratings =
                ratingService.getRatingsByUser(username);

        myCommentsListView.getItems().setAll(comments);

        likedListView.getItems().setAll(likedArtworks);

        List<String> ratingTexts = ratings.stream()
                .map(rating -> {

                    Artwork artwork = ArtworkService.findById(
                            rating.getArtworkId()
                    );

                    String title = artwork != null
                            ? artwork.getTitle()
                            : rating.getArtworkId();

                    return title + " - "
                            + rating.getStars() + " stars";

                })
                .collect(Collectors.toList());

        ratingsListView.getItems().setAll(ratingTexts);

        summaryLabel.setText(
                comments.size() + " comments, "
                + likedArtworks.size() + " likes, "
                + ratings.size() + " ratings"
        );

        likedListView.setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.PRIMARY
                    && event.getClickCount() == 2) {

                Artwork selected = likedListView
                        .getSelectionModel()
                        .getSelectedItem();

                if (selected != null) {

                    UserSession.setSelectedArtwork(selected);

                    SceneNavigator.switchTo(
                            "ArtworkDetailsView.fxml"
                    );
                }
            }
        });
    }

    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("GalleryView.fxml");
    }
}