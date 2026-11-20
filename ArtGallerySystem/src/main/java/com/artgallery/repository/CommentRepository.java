// =====================================================================
// File: CommentRepository.java
// Package: com.artgallery.repository
// Layer: PERSISTENCE  -  Requirement R4
// Data file: data/comments.txt
// =====================================================================
//
// PURPOSE
// -------
// Loads comments from data/comments.txt and saves them back every time
// a new comment is added.
//
// LINE FORMAT
// -----------
//   commentId|username|artworkId|date|text
//
//   (text is placed LAST so it is easy to read; the "|" character is
//    not allowed inside comment text - checked by InputValidator.)
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadAll()  -> List<Comment>
// - saveAll(List<Comment> comments)
//
// ERROR HANDLING (R6)
// -------------------
// - Skip broken lines, catch IOException through FileManager.
// =====================================================================
