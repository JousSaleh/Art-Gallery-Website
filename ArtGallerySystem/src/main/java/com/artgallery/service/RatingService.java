
package com.artgallery.service;

import com.artgallery.model.Rating;
import com.artgallery.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

public class RatingService {

    private final RatingRepository ratingRepository;
    private List<Rating> ratings;

    public RatingService() {
        this.ratingRepository = new RatingRepository();
        this.ratings = new ArrayList<>();
    }

    // Load ratings from file
    public void loadRatings() {
        ratings = ratingRepository.loadAll();
    }

    // Add or update a rating
    public void rateArtwork(String username, String artworkId, int stars) {

        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5 stars."
            );
        }

        Rating existingRating = ratings.stream()
                .filter(rating ->
                        rating.getUsername().equals(username)
                        && rating.getArtworkId().equals(artworkId))
                .findFirst()
                .orElse(null);

        if (existingRating != null) {
            existingRating.setStars(stars);
        } else {
            ratings.add(new Rating(username, artworkId, stars));
        }

        ratingRepository.saveAll(ratings);
    }

    // Get user's previous rating
    public int getUserRating(String username, String artworkId) {
        return ratings.stream()
                .filter(rating ->
                        rating.getUsername().equals(username)
                        && rating.getArtworkId().equals(artworkId))
                .mapToInt(Rating::getStars)
                .findFirst()
                .orElse(0);
    }

    // Calculate average rating
    public double getAverageRating(String artworkId) {
        return ratings.stream()
                .filter(rating ->
                        rating.getArtworkId().equals(artworkId))
                .mapToInt(Rating::getStars)
                .average()
                .orElse(0.0);
    }

    // Count ratings for an artwork
    public long countRatings(String artworkId) {
        return ratings.stream()
                .filter(rating ->
                        rating.getArtworkId().equals(artworkId))
                .count();
    }

    // Get all ratings by a specific user
    public List<Rating> getRatingsByUser(String username) {
        return ratings.stream()
                .filter(rating ->
                        rating.getUsername().equals(username))
                .toList();
    }
}
