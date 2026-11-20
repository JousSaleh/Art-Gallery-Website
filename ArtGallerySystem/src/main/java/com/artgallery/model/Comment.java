// =====================================================================
// File: Comment.java
// Package: com.artgallery.model
// Layer: MODEL (MVC)  -  Requirement R3
// =====================================================================
//
// PURPOSE
// -------
// Represents ONE comment written by a user on an artwork.
// Simple POJO, no GUI code, no file code.
//
// ATTRIBUTES TO ADD LATER (all private)
// -------------------------------------
// - id         : String -> unique comment id, e.g. "C1", "C2"
//                          (CommentService will generate it)
// - username   : String -> who wrote the comment     (link to User)
// - artworkId  : String -> which artwork it belongs to (link to Artwork)
// - text       : String -> the comment itself
// - date       : String -> date when it was written, e.g. "2026-11-20"
//                          (you can use LocalDate.now().toString())
//
// WHAT TO WRITE LATER
// -------------------
// 1. Constructor with all attributes.
// 2. Getters.
// 3. toString() -> something like  "sara (2026-11-20): Beautiful colors!"
//    so the comment looks good when shown directly in a ListView.
//
// RELATIONSHIPS
// -------------
// - Many Comments belong to ONE Artwork  (artworkId).
// - Many Comments belong to ONE User     (username).
//
// NOTE
// ----
// The text must NOT contain the "|" character because "|" is used as
// the separator in comments.txt. InputValidator will check this (R6).
// =====================================================================
