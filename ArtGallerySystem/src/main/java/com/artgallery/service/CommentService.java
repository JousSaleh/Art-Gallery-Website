// =====================================================================
// File: CommentService.java
// Package: com.artgallery.service
// Layer: SERVICE  -  Requirements R3, R4, R5
// =====================================================================
//
// PURPOSE
// -------
// Manages comments: adding new comments and finding comments by
// artwork or by user. No javafx imports here.
//
// DATA IT KEEPS (later)
// ---------------------
// - A List<Comment> loaded from CommentRepository at start-up.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadComments()
//     Read data/comments.txt through CommentRepository.
// - addComment(String username, String artworkId, String text)
//     1. Generate a new id, e.g. "C" + (list size + 1).
//     2. Use today's date.
//     3. Create a Comment object and add it to the list.
//     4. Save all comments with CommentRepository.saveAll(...)   (R4)
// - getCommentsForArtwork(String artworkId)
//     filter(c -> c.getArtworkId().equals(artworkId))
//     + sorted by date (newest first)                           (R5)
// - getCommentsByUser(String username)
//     filter(c -> c.getUsername().equals(username))             (R5)
// - countCommentsForArtwork(String artworkId)  (optional)
//     filter(...).count()
//
// BUSINESS RULES
// --------------
// - Only logged-in users can comment (the controller checks UserSession).
// - Text is already validated by the controller (InputValidator) before
//   calling addComment.
// =====================================================================
