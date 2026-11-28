package com.artgallery.controller;

import com.artgallery.model.ArtworkCategory;
import com.artgallery.service.StatisticsService;
import com.artgallery.util.SceneNavigator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.Map;

public class StatisticsController {

    // Statistics labels
    @FXML private Label totalArtworksLabel;
    @FXML private Label oilCountLabel;
    @FXML private Label pastelCountLabel;
    @FXML private Label totalLikesLabel;
    @FXML private Label totalCommentsLabel;

    // Rankings
    @FXML private ListView<String> mostLikedListView;
    @FXML private ListView<String> topRatedListView;

    // Called automatically when the FXML screen loads
    @FXML
    public void initialize() {

        // Messages displayed when the lists are empty
        mostLikedListView.setPlaceholder(
                new Label("No likes yet")
        );

        topRatedListView.setPlaceholder(
                new Label("No ratings yet")
        );

        refreshStatistics();
    }

    // Calculate statistics and update the screen
    private void refreshStatistics() {

        // Create a new service to load the latest saved data
        StatisticsService statistics = new StatisticsService();

        // Get artwork counts by category
        Map<ArtworkCategory, Long> categoryCounts =
                statistics.countArtworksPerCategory();

        long oilCount = categoryCounts.getOrDefault(
                ArtworkCategory.OIL, 0L
        );

        long pastelCount = categoryCounts.getOrDefault(
                ArtworkCategory.PASTEL, 0L
        );

        // Update the labels
        totalArtworksLabel.setText(
                "Total artworks: " + statistics.getTotalArtworks()
        );

        oilCountLabel.setText(
                "Oil paintings: " + oilCount
        );

        pastelCountLabel.setText(
                "Pastel paintings: " + pastelCount
        );

        totalLikesLabel.setText(
                "Total likes: " + statistics.getTotalLikes()
        );

        totalCommentsLabel.setText(
                "Total comments: " + statistics.getTotalComments()
        );

        // Display the top 5 most-liked artworks
        mostLikedListView.setItems(
                FXCollections.observableArrayList(
                        statistics.getMostLikedArtworks(5)
                )
        );

        // Display the top 5 highest-rated artworks
        topRatedListView.setItems(
                FXCollections.observableArrayList(
                        statistics.getTopRatedArtworks(5)
                )
        );
    }

    // Refresh button event
    @FXML
    private void handleRefresh() {
        refreshStatistics();
    }

    // Back button event
    @FXML
    private void handleBack() {
        SceneNavigator.switchTo("GalleryView.fxml");
    }
}