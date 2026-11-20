// =====================================================================
// File: Like.java
// Package: com.artgallery.model
// Layer: MODEL (MVC)  -  Requirement R3
// =====================================================================
//
// PURPOSE
// -------
// Represents ONE "like" given by a user to an artwork.
// Simple POJO, no GUI code, no file code.
//
// ATTRIBUTES TO ADD LATER (all private)
// -------------------------------------
// - username  : String -> who liked        (link to User)
// - artworkId : String -> which artwork    (link to Artwork)
//
// WHAT TO WRITE LATER
// -------------------
// 1. Constructor with both attributes.
// 2. Getters.
//
// RULES (checked in LikeService, not here)
// ----------------------------------------
// - A user can like the same artwork only ONCE.
// - Clicking the like button again should "unlike" (remove the Like).
//
// RELATIONSHIPS
// -------------
// - Many Likes belong to ONE Artwork.
// - Many Likes belong to ONE User.
// - The number of likes of an artwork = how many Like objects have
//   that artworkId (counted with a Stream in LikeService - R5).
// =====================================================================
