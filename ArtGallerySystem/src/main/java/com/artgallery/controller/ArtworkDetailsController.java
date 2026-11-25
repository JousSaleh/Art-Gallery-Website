
package com.artgallery.controller;

import com.artgallery.model.Artwork;
import com.artgallery.service.LikeService;
import com.artgallery.service.RatingService;
import com.artgallery.service.CommentService;
import com.artgallery.util.ImageHelper;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;

public class ArtworkDetailsController {

    @FXML private javafx.scene.image.ImageView artworkImageView;
    @FXML private Label titleLabel;
    @FXML private Label artistLabel;
    @FXML private Label categoryLabel;
    @FXML private TextArea descriptionArea;

    // Likes
    @FXML private Button likeButton;
    @FXML private Label likesCountLabel;

    // Ratings
    @FXML private Slider ratingSlider;
    @FXML private Label ratingValueLabel;
    @FXML private Button submitRatingButton;
    @FXML private Label averageRatingLabel;

    // Comments
    @FXML private ListView<com.artgallery.model.Comment> commentsListView;
    @FXML private TextArea commentArea;
    @FXML private Label charCountLabel;
    @FXML private Button addCommentButton;

    @FXML private Label messageLabel;

    private static final String CURRENT_USERNAME = "TestUser";

    private final LikeService likeService = new LikeService();
    private final RatingService ratingService = new RatingService();

    private Artwork artwork;

    @FXML
    public void initialize() {

        likeService.loadLikes();
        ratingService.loadRatings();
        CommentService.loadComments();

        artwork = GalleryController.getSelectedArtwork();

        if (artwork == null) {
            messageLabel.setText("Artwork not found.");
            return;
        }

        displayArtwork();

        updateLikeDisplay();
        updateRatingDisplay();
        updateCommentsDisplay();

        likeButton.setOnAction(e -> handleLike());
        submitRatingButton.setOnAction(e -> handleRating());
        addCommentButton.setOnAction(e -> handleAddComment());

        ratingSlider.valueProperty().addListener(
            (obs, oldValue, newValue) ->
                ratingValueLabel.setText(
                    Math.round(newValue.doubleValue()) + " stars"
                )
        );

        ratingValueLabel.setText(
            Math.round(ratingSlider.getValue()) + " stars"
        );

        commentArea.textProperty().addListener(
            (obs, oldText, newText) -> {
                int length = newText.length();
                charCountLabel.setText(length + " / 200");
                addCommentButton.setDisable(newText.trim().isEmpty());
            }
        );

        addCommentButton.setDisable(true);
        charCountLabel.setText("0 / 200");
    }

    private void displayArtwork() {

        titleLabel.setText(artwork.getTitle());
        artistLabel.setText(artwork.getArtist());
        categoryLabel.setText(artwork.getCategory().toString());

        descriptionArea.setText(artwork.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setWrapText(true);

        Image image = ImageHelper.loadImage(artwork.getImagePath());

        if (image != null) {
            artworkImageView.setImage(image);
        } else {
            messageLabel.setText("Artwork image not found.");
        }
    }

    // =========================
    // Likes
    // =========================

    private void handleLike() {

        boolean liked = likeService.toggleLike(
            CURRENT_USERNAME, artwork.getId()
        );

        updateLikeDisplay();

        messageLabel.setText(
            liked ? "You liked this artwork!"
                  : "You unliked this artwork."
        );
    }

    private void updateLikeDisplay() {

        boolean liked = likeService.hasLiked(
            CURRENT_USERNAME, artwork.getId()
        );

        long count = likeService.countLikes(artwork.getId());

        likeButton.setText(liked ? "Unlike" : "Like");
        likesCountLabel.setText(count + " likes");
    }

    // =========================
    // Ratings
    // =========================

    private void handleRating() {

        int stars = (int) Math.round(ratingSlider.getValue());

        try {
            ratingService.rateArtwork(
                CURRENT_USERNAME, artwork.getId(), stars
            );

            updateRatingDisplay();
            messageLabel.setText("Rating saved successfully!");

        } catch (IllegalArgumentException e) {
            messageLabel.setText(e.getMessage());
        }
    }

    private void updateRatingDisplay() {

        int oldRating = ratingService.getUserRating(
            CURRENT_USERNAME, artwork.getId()
        );

        if (oldRating > 0) {
            ratingSlider.setValue(oldRating);
        }

        double average = ratingService.getAverageRating(artwork.getId());
        long count = ratingService.countRatings(artwork.getId());

        averageRatingLabel.setText(
            String.format("Average: %.1f / 5 (%d ratings)", average, count)
        );

        ratingValueLabel.setText(
            Math.round(ratingSlider.getValue()) + " stars"
        );
    }

    // =========================
    // Comments
    // =========================

    private void updateCommentsDisplay() {

        commentsListView.setItems(
            FXCollections.observableArrayList(
                CommentService.getCommentsForArtwork(artwork.getId())
            )
        );
    }

    private void handleAddComment() {

        String commentText = commentArea.getText();

        try {
            CommentService.addComment(
                CURRENT_USERNAME,
                artwork.getId(),
                commentText
            );

            updateCommentsDisplay();

            commentArea.clear();

            messageLabel.setText("Comment added successfully!");

        } catch (IllegalArgumentException e) {

            messageLabel.setText(e.getMessage());
        }
    }
}
