// =====================================================================
// File: Artwork.java
// Package: com.artgallery.model
// Layer: MODEL (MVC)  -  Requirement R3
// =====================================================================
//
// PURPOSE
// -------
// Represents ONE artwork shown in the gallery.
// This is a simple POJO (Plain Old Java Object), exactly like the
// "Student" model in Lecture 9. It only carries data.
//
// IMPORTANT RULES (from Lecture 9 - "Models data, nothing else!")
// ---------------------------------------------------------------
// - Do NOT import anything from javafx.* in this file.
// - Do NOT read or write files here (that is the repository's job).
// - Do NOT show alerts or messages here (that is the controller's job).
//
// ATTRIBUTES TO ADD LATER (all private)
// -------------------------------------
// - id           : String  -> unique artwork id, e.g. "A1", "A2"
// - title        : String  -> name of the painting
// - artist       : String  -> name of the artist
// - description  : String  -> short text about the painting
// - category     : ArtworkCategory -> OIL or PASTEL (see ArtworkCategory.java)
// - imagePath    : String  -> image file name relative to the images folder,
//                             e.g. "oil/sunset.jpg" or "pastel/flowers.jpg"
// - year         : int     -> (optional) year the painting was made
//
// WHAT TO WRITE LATER
// -------------------
// 1. A constructor that receives all the attributes above.
// 2. A getter for every attribute.
// 3. Setters only if you really need them (artworks are added by the
//    developer, so most fields will never change while the app runs).
// 4. toString() -> return the title (useful when the artwork is shown
//    inside a ListView, because ListView calls toString()).
//
// RELATIONSHIPS
// -------------
// - One Artwork has ONE ArtworkCategory.
// - One Artwork can have MANY Comment, Like and Rating objects.
//   The link is made by storing the artwork id inside Comment, Like and
//   Rating (we do NOT store lists of comments inside Artwork, to keep
//   the model simple and the files easy to save).
//
// WHO CREATES ARTWORK OBJECTS?
// ----------------------------
// Only ArtworkRepository, when it reads data/artworks.txt at start-up.
// Regular users can never create, edit or delete artworks.
// =====================================================================
