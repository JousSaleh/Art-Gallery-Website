package com.artgallery.controller;

import com.artgallery.model.Artwork;
import com.artgallery.model.ArtworkCategory;
import com.artgallery.service.ArtworkService;
import com.artgallery.util.ImageHelper;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GalleryController {

    @FXML private Label welcomeLabel;
    @FXML private TextField searchField;
    @FXML private RadioButton allRadio;
    @FXML private RadioButton oilRadio;
    @FXML private RadioButton pastelRadio;
    @FXML private ToggleGroup categoryGroup;
    @FXML private ComboBox<String> sortComboBox;
    @FXML private FlowPane artworkFlowPane;
    @FXML private Label resultCountLabel;
    @FXML private Button myActivityButton;
    @FXML private Button statisticsButton;
    @FXML private Button logoutButton;

    @FXML
    public void initialize() {
        // TODO (after Girl 1 finishes UserSession):
        // welcomeLabel.setText("Welcome, " + UserSession.getCurrentUser().getFullName());
        welcomeLabel.setText("Welcome to the Art Gallery");

        sortComboBox.getItems().addAll(
                ArtworkService.SORT_TITLE_AZ,
                ArtworkService.SORT_TITLE_ZA,
                ArtworkService.SORT_NEWEST,
                ArtworkService.SORT_OLDEST);
        sortComboBox.setValue(ArtworkService.SORT_TITLE_AZ);

        // Property event: live search while typing (R2)
        searchField.textProperty().addListener((obs, oldText, newText) -> refreshGallery());

        // Property event: category radio buttons
        categoryGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> refreshGallery());

        // Action events (R2) written as lambdas (R5)
        sortComboBox.setOnAction(e -> refreshGallery());
        myActivityButton.setOnAction(e -> openMyActivity());
        statisticsButton.setOnAction(e -> openStatistics());
        logoutButton.setOnAction(e -> logout());

        refreshGallery();
    }

    private void refreshGallery() {
        List<Artwork> result = ArtworkService.search(searchField.getText(), getSelectedCategory());
        result = ArtworkService.sort(result, sortComboBox.getValue());

        artworkFlowPane.getChildren().clear();

        if (result.isEmpty()) {
            artworkFlowPane.getChildren().add(new Label("No artworks found."));
        } else {
            result.forEach(artwork -> artworkFlowPane.getChildren().add(createArtworkCard(artwork)));
        }

        int total = ArtworkService.getAllArtworks().size();
        resultCountLabel.setText("Showing " + result.size() + " of " + total + " artworks");
    }

    // null means "All"
    private ArtworkCategory getSelectedCategory() {
        Toggle selected = categoryGroup.getSelectedToggle();
        if (selected == oilRadio) {
            return ArtworkCategory.OIL;
        } else if (selected == pastelRadio) {
            return ArtworkCategory.PASTEL;
        }
        return null;
    }

    private VBox createArtworkCard(Artwork artwork) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setPrefWidth(220);
        card.setCursor(Cursor.HAND);
        card.getStyleClass().add("artwork-card");
        card.getStyleClass().add(artwork.getCategory() == ArtworkCategory.OIL ? "oil-card" : "pastel-card");

        Image image = ImageHelper.loadImage(artwork.getImagePath());
        if (image != null) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            card.getChildren().add(imageView);
        } else {
            Label noImage = new Label("Image not found");
            noImage.setPrefSize(200, 150);
            noImage.setAlignment(Pos.CENTER);
            card.getChildren().add(noImage);
        }

        Label titleLabel = new Label(artwork.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.getStyleClass().add("card-title");

        Label infoLabel = new Label(artwork.getArtist() + " - " + artwork.getYear());
        Label categoryLabel = new Label(artwork.getCategory().getDisplayName());

        card.getChildren().addAll(titleLabel, infoLabel, categoryLabel);

        // Mouse event: click a card to open its details (R2)
        card.setOnMouseClicked(event -> openDetails(artwork));

        return card;
    }

    private void openDetails(Artwork artwork) {
        // TODO (after Girl 1 finishes UserSession + SceneNavigator):
        // UserSession.setSelectedArtwork(artwork);
        // SceneNavigator.switchTo("ArtworkDetailsView.fxml");
        System.out.println("Clicked: " + artwork.getTitle());   // temporary test
    }

    private void openMyActivity() {
        // TODO: SceneNavigator.switchTo("MyActivityView.fxml");
        System.out.println("My Activity clicked");
    }

    private void openStatistics() {
        // TODO: SceneNavigator.switchTo("StatisticsView.fxml");
        System.out.println("Statistics clicked");
    }

    private void logout() {
        // TODO: UserSession.clear();
        //       SceneNavigator.switchTo("WelcomeView.fxml");
        System.out.println("Logout clicked");
    }
}