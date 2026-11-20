// =====================================================================
// File: Rating.java
// Package: com.artgallery.model
// Layer: MODEL (MVC)  -  Requirement R3
// =====================================================================
//
// PURPOSE
// -------
// Represents ONE star rating given by a user to an artwork.
// Simple POJO, no GUI code, no file code.
//
// ATTRIBUTES TO ADD LATER (all private)
// -------------------------------------
// - username  : String -> who rated        (link to User)
// - artworkId : String -> which artwork    (link to Artwork)
// - stars     : int    -> value from 1 to 5
//
// WHAT TO WRITE LATER
// -------------------
// 1. Constructor with all attributes.
// 2. Getters.
// 3. A setter for "stars" (so a user can change his rating).
//
// RULES (checked in RatingService / InputValidator, not here)
// -----------------------------------------------------------
// - stars must be between 1 and 5.
// - A user has only ONE rating per artwork. If he rates again,
//   the old rating is updated instead of adding a new one.
//
// RELATIONSHIPS
// -------------
// - Many Ratings belong to ONE Artwork.
// - Many Ratings belong to ONE User.
// - The average rating of an artwork is calculated with a Stream
//   (mapToInt(...).average()) in RatingService - R5.
// =====================================================================
