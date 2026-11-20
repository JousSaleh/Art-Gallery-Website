// =====================================================================
// File: LikeRepository.java
// Package: com.artgallery.repository
// Layer: PERSISTENCE  -  Requirement R4
// Data file: data/likes.txt
// =====================================================================
//
// PURPOSE
// -------
// Loads likes from data/likes.txt and saves them back every time a user
// likes or unlikes an artwork.
//
// LINE FORMAT
// -----------
//   username|artworkId
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadAll()  -> List<Like>
// - saveAll(List<Like> likes)
//
// ERROR HANDLING (R6)
// -------------------
// - Skip broken lines, catch IOException through FileManager.
// =====================================================================
