// =====================================================================
// File: StatisticsService.java
// Package: com.artgallery.service
// Layer: SERVICE  -  Requirement R5 (Stream API + Collectors report)
// =====================================================================
//
// PURPOSE
// -------
// Calculates the numbers shown on the Gallery Statistics screen.
// It does NOT keep its own data; it asks the other services for their
// lists and processes them with Streams. No javafx imports here.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - countArtworksPerCategory()
//     Return a Map<ArtworkCategory, Long> using:
//       artworks.stream()
//               .collect(Collectors.groupingBy(Artwork::getCategory,
//                                              Collectors.counting()));
//     <-- the required "report with Collectors" (R5)
//
// - getMostLikedArtworks(int limit)
//     Sort artworks by LikeService.countLikes(id) (highest first),
//     limit(limit), and map each one to a String like
//     "Sunset - 12 likes".                         (sorted + map, R5)
//
// - getTopRatedArtworks(int limit)
//     Sort by RatingService.getAverageRating(id) (highest first),
//     limit(limit), map to "Sunset - 4.5 / 5".
//
// - getAverageRatingPerCategory()   (optional, extra Collectors practice)
//     Use Collectors.groupingBy(category, Collectors.averagingDouble(...)).
//
// - getTotalArtworks(), getTotalLikes(), getTotalComments()  (simple counts)
//
// NOTES
// -----
// - Make sure nothing crashes when lists are empty (return empty maps /
//   lists, and 0 for averages).
// =====================================================================
package com.artgallery.service;

import com.artgallery.model.Artwork;
import com.artgallery.model.ArtworkCategory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsService {

    private final LikeService likeService;
    private final RatingService ratingService;

    public StatisticsService() {

        likeService = new LikeService();
        ratingService = new RatingService();

        // Load the latest saved interactions
        likeService.loadLikes();
        ratingService.loadRatings();
    }

    // Count artworks in each category using Collectors (R5)
    public Map<ArtworkCategory, Long> countArtworksPerCategory() {

        return ArtworkService.getAllArtworks()
                .stream()
                .collect(Collectors.groupingBy(
                        Artwork::getCategory,
                        Collectors.counting()
                ));
    }

    // Get the most liked artworks
    public List<String> getMostLikedArtworks(int limit) {

        return ArtworkService.getAllArtworks()
                .stream()
                .filter(artwork -> likeService.countLikes(artwork.getId()) > 0)
                .sorted(Comparator.comparingLong(
                        (Artwork artwork) ->
                                likeService.countLikes(artwork.getId())
                ).reversed())
                .limit(Math.max(0, limit))
                .map(artwork ->
                        artwork.getTitle() + " - "
                        + likeService.countLikes(artwork.getId())
                        + " likes")
                .toList();
    }

    // Get the highest rated artworks
    public List<String> getTopRatedArtworks(int limit) {

        return ArtworkService.getAllArtworks()
                .stream()
                .filter(artwork -> ratingService.countRatings(artwork.getId()) > 0)
                .sorted(Comparator.comparingDouble(
                        (Artwork artwork) ->
                                ratingService.getAverageRating(artwork.getId())
                ).reversed())
                .limit(Math.max(0, limit))
                .map(artwork ->
                        String.format(
                                "%s - %.1f / 5",
                                artwork.getTitle(),
                                ratingService.getAverageRating(artwork.getId())
                        ))
                .toList();
    }

    // Total number of artworks
    public long getTotalArtworks() {

        return ArtworkService.getAllArtworks().size();
    }

    // Total likes across the artworks
    public long getTotalLikes() {

        return ArtworkService.getAllArtworks()
                .stream()
                .mapToLong(artwork ->
                        likeService.countLikes(artwork.getId()))
                .sum();
    }

    // Total comments
    public long getTotalComments() {

        return CommentService.countCommentsPerArtwork()
                .values()
                .stream()
                .mapToLong(Number::longValue)
                .sum();
    }
}