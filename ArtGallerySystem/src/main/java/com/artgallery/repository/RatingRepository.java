// =====================================================================
// File: RatingRepository.java
// Package: com.artgallery.repository
// Layer: PERSISTENCE  -  Requirement R4
// Data file: data/ratings.txt
// =====================================================================
//
// PURPOSE
// -------
// Loads ratings from data/ratings.txt and saves them back every time a
// user rates (or re-rates) an artwork.
//
// LINE FORMAT
// -----------
//   username|artworkId|stars
//
//   (stars is a whole number from 1 to 5; use Integer.parseInt when
//    loading and catch NumberFormatException.)
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadAll()  -> List<Rating>
// - saveAll(List<Rating> ratings)
//
// ERROR HANDLING (R6)
// -------------------
// - Skip broken lines and invalid numbers instead of crashing.
// =====================================================================
