// =====================================================================
// File: MyActivityController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R1, R2, R3, R5
// Paired view: resources/com/artgallery/view/MyActivityView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Shows the logged-in user's own activity:
//   - the comments he wrote,
//   - the artworks he liked,
//   - the ratings he gave.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in MyActivityView.fxml)
// ------------------------------------------------------------------
// - userLabel          : Label           ("Activity of: sara")
// - myCommentsListView : ListView<Comment>
// - likedListView      : ListView<Artwork>
// - ratingsListView    : ListView<String>  (e.g. "Sunset - 4 stars")
//                        (a TableView with two columns is also fine)
// - summaryLabel       : Label  ("3 comments, 5 likes, 2 ratings")
// - backButton         : Button
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Mouse event: double-click an item in likedListView ->
//   open that artwork in ArtworkDetailsView.fxml.
// - Action event: backButton -> GalleryView.fxml
//
// FUNCTIONAL PROGRAMMING (R5)
// ---------------------------
// The lists come from the services, which use Streams like:
//   comments.stream().filter(c -> c.getUsername().equals(user))...
//   likes.stream().filter(...).map(like -> findArtwork(like.getArtworkId()))...
// The ratings list can use map(...) to turn each Rating into a String.
//
// INPUT VALIDATION / ERRORS (R6)
// ------------------------------
// - If a list is empty, show a placeholder text in the ListView
//   (listView.setPlaceholder(new Label("You have not liked any artwork yet."))).
//
// NAVIGATION
// ----------
// - Back -> "GalleryView.fxml"
// - Double-click liked artwork -> UserSession.setSelectedArtwork(...);
//   "ArtworkDetailsView.fxml"
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// - UserSession.getCurrentUser()
// - CommentService.getCommentsByUser(username)
// - LikeService.getLikedArtworks(username)
// - RatingService.getRatingsByUser(username)
// =====================================================================
