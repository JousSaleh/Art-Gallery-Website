
package com.artgallery.controller;

import com.artgallery.Main;
import com.artgallery.model.Artwork;
import com.artgallery.model.Comment;
import com.artgallery.model.User;
import com.artgallery.service.CommentService;
import com.artgallery.service.LikeService;
import com.artgallery.service.RatingService;
import com.artgallery.util.ImageHelper;
import com.artgallery.util.SceneNavigator;
import com.artgallery.util.UserSession;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class ArtworkDetailsController {

    // Artwork information
    @FXML private ImageView artworkImageView;
    @FXML private Label titleLabel;
    @FXML private Label artistLabel;
    @FXML private Label categoryLabel;
    @FXML private TextArea descriptionArea;
    @FXML private Label yearLabel;
    

    // Back button
    @FXML private Button backButton;

    // Like
    @FXML private Button likeButton;
    @FXML private Label likesCountLabel;

    // Rating
    @FXML private Slider ratingSlider;
    @FXML private Label ratingValueLabel;
    @FXML private Button submitRatingButton;
    @FXML private Label averageRatingLabel;

    // Comments
    @FXML private ListView<Comment> commentsListView;
    @FXML private TextArea commentArea;
    @FXML private Label charCountLabel;
    @FXML private Button addCommentButton;

    // Feedback
    @FXML private Label messageLabel;

    // Shared services
    private LikeService likeService;
    private RatingService ratingService;

    private Artwork artwork;
    private String currentUsername;

    @FXML
    public void initialize() {

        // Connect shared services
        likeService = Main.getLikeService();
        ratingService = Main.getRatingService();

        // Get logged-in user
        User currentUser = UserSession.getCurrentUser();

        if (currentUser == null) {
            messageLabel.setText("Please log in first.");
            disableControls();
            return;
        }

        currentUsername = currentUser.getUsername();

        // Get selected artwork
        artwork = UserSession.getSelectedArtwork();

        if (artwork == null) {
            messageLabel.setText("Artwork not found.");
            disableControls();
            return;
        }

        // Load comments
        CommentService.loadComments();

        // Display artwork and interactions
        displayArtwork();
        updateLikeDisplay();
        updateRatingDisplay();
        updateCommentsDisplay();

        // Back button
        backButton.setOnAction(e -> goBack());

        // Like button
        likeButton.setOnAction(e -> handleLike());

        // Rating button
        submitRatingButton.setOnAction(e -> handleRating());

        // Add comment button
        addCommentButton.setOnAction(e -> handleAddComment());

        // Rating slider listener
        ratingSlider.valueProperty().addListener(
            (obs, oldValue, newValue) -> {
                ratingValueLabel.setText(
                    Math.round(newValue.doubleValue()) + " stars"
                );
            }
        );

        ratingValueLabel.setText(
            Math.round(ratingSlider.getValue()) + " stars"
        );

        // Comment character counter
        commentArea.textProperty().addListener(
            (obs, oldText, newText) -> {

                int length = newText.length();

                charCountLabel.setText(length + " / 200");

                addCommentButton.setDisable(
                    newText.trim().isEmpty() || length > 200
                );
            }
        );

        addCommentButton.setDisable(true);
        charCountLabel.setText("0 / 200");
    }

    // =========================================
    // Display Artwork
    // =========================================

    private void displayArtwork() {

        titleLabel.setText(artwork.getTitle());

        artistLabel.setText(artwork.getArtist());

        categoryLabel.setText(
            artwork.getCategory().getDisplayName()
        );

        descriptionArea.setText(
            artwork.getDescription()
        );

        Image image = ImageHelper.loadImage(
            artwork.getImagePath()
        );

        if (image != null) {
            artworkImageView.setImage(image);
        }
        
        yearLabel.setText(String.valueOf(artwork.getYear()));

    }

    // =========================================
    // Like / Unlike
    // =========================================

    private void handleLike() {

        boolean liked = likeService.toggleLike(
            currentUsername,
            artwork.getId()
        );

        if (liked) {
            messageLabel.setText("You liked this artwork.");
        } else {
            messageLabel.setText("You removed your like.");
        }

        updateLikeDisplay();
    }

    private void updateLikeDisplay() {

        boolean liked = likeService.hasLiked(
            currentUsername,
            artwork.getId()
        );

        likeButton.setText(
            liked ? "Unlike" : "Like"
        );

        long count = likeService.countLikes(
            artwork.getId()
        );

        likesCountLabel.setText(
            count + " likes"
        );
    }

    // =========================================
    // Rating
    // =========================================

    private void handleRating() {

        int stars = (int) Math.round(
            ratingSlider.getValue()
        );

        ratingService.rateArtwork(
            currentUsername,
            artwork.getId(),
            stars
        );

        messageLabel.setText(
            "Your rating has been submitted."
        );

        updateRatingDisplay();
    }

    private void updateRatingDisplay() {

        int userRating = ratingService.getUserRating(
            currentUsername,
            artwork.getId()
        );

        if (userRating > 0) {
            ratingSlider.setValue(userRating);
        }

        double average = ratingService.getAverageRating(
            artwork.getId()
        );

        long count = ratingService.countRatings(
            artwork.getId()
        );

        averageRatingLabel.setText(
            String.format(
                "Average: %.1f / 5 (%d ratings)",
                average,
                count
            )
        );
    }

    // =========================================
    // Comments
    // =========================================

    private void updateCommentsDisplay() {

        List<Comment> comments =
            CommentService.getCommentsForArtwork(
                artwork.getId()
            );

        commentsListView.setItems(
            FXCollections.observableArrayList(comments)
        );

        commentsListView.setCellFactory(listView ->
            new ListCell<Comment>() {

                @Override
                protected void updateItem(
                    Comment comment,
                    boolean empty
                ) {
                    super.updateItem(comment, empty);

                    if (empty || comment == null) {
                        setText(null);
                    } else {
                        setText(
                            comment.getUsername()
                            + " (" + comment.getDate() + "): "
                            + comment.getText()
                        );
                    }
                }
            }
        );
    }

    private void handleAddComment() {

        String text = commentArea.getText().trim();

        if (text.isEmpty()) {
            messageLabel.setText(
                "Please write a comment first."
            );
            return;
        }

        if (text.length() > 200) {
            messageLabel.setText(
                "Comment cannot exceed 200 characters."
            );
            return;
        }

        try {

            CommentService.addComment(
                currentUsername,
                artwork.getId(),
                text
            );

            commentArea.clear();

            updateCommentsDisplay();

            messageLabel.setText(
                "Your comment has been added."
            );

        } catch (IllegalArgumentException e) {

            messageLabel.setText(e.getMessage());

        }
    }

    // =========================================
    // Back to Gallery
    // =========================================

    private void goBack() {

        SceneNavigator.switchTo("GalleryView.fxml");

    }

    // =========================================
    // Disable Controls
    // =========================================

    private void disableControls() {

        likeButton.setDisable(true);
        submitRatingButton.setDisable(true);
        ratingSlider.setDisable(true);
        commentArea.setDisable(true);
        addCommentButton.setDisable(true);
        backButton.setDisable(false);

    }

}
