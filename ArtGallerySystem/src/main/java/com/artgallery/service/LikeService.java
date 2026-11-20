// =====================================================================
// File: LikeService.java
// Package: com.artgallery.service
// Layer: SERVICE  -  Requirements R3, R4, R5
// =====================================================================
//
// PURPOSE
// -------
// Manages likes: like / unlike an artwork, count likes, and find what a
// user has liked. No javafx imports here.
//
// DATA IT KEEPS (later)
// ---------------------
// - A List<Like> loaded from LikeRepository at start-up.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadLikes()
//     Read data/likes.txt through LikeRepository.
// - hasLiked(String username, String artworkId)
//     anyMatch(...) on the list                                  (R5)
// - toggleLike(String username, String artworkId)
//     If already liked -> remove it (removeIf with a lambda).
//     Otherwise        -> add a new Like.
//     Then save with LikeRepository.saveAll(...)                 (R4)
//     Return true if the artwork is now liked, false if unliked.
// - countLikes(String artworkId)
//     filter(l -> l.getArtworkId().equals(artworkId)).count()    (R5)
// - getLikedArtworks(String username)
//     filter by username, then map(l -> ArtworkService.findById(...))
//     and collect to a List<Artwork>.                            (R5)
//
// BUSINESS RULES
// --------------
// - One like per user per artwork (no duplicates).
// =====================================================================
