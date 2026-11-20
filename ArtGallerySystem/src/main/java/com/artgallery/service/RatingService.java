// =====================================================================
// File: RatingService.java
// Package: com.artgallery.service
// Layer: SERVICE  -  Requirements R3, R4, R5, R6
// =====================================================================
//
// PURPOSE
// -------
// Manages star ratings (1 to 5): rate an artwork, update a rating and
// calculate averages. No javafx imports here.
//
// DATA IT KEEPS (later)
// ---------------------
// - A List<Rating> loaded from RatingRepository at start-up.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadRatings()
//     Read data/ratings.txt through RatingRepository.
// - rateArtwork(String username, String artworkId, int stars)
//     1. If stars is not between 1 and 5 -> throw
//        IllegalArgumentException (the controller catches it and shows
//        an error) (R6).
//     2. If the user already rated this artwork -> update the stars.
//        Otherwise -> add a new Rating.
//     3. Save with RatingRepository.saveAll(...)                 (R4)
// - getUserRating(String username, String artworkId)
//     Return the user's old rating (or 0 / empty if none).
// - getAverageRating(String artworkId)
//     filter by artworkId, mapToInt(Rating::getStars).average()
//     .orElse(0)                                                 (R5)
// - countRatings(String artworkId)
// - getRatingsByUser(String username)
//     filter by username (for the My Activity screen).
//
// BUSINESS RULES
// --------------
// - One rating per user per artwork (rating again = update).
// - Average is 0 (or "No ratings yet") when there are no ratings.
// =====================================================================
