// =====================================================================
// File: ArtworkDetailsController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R1, R2, R3, R4, R6
// Paired view: resources/com/artgallery/view/ArtworkDetailsView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Shows ONE selected artwork in a large size with all its information,
// and lets the logged-in user like it, rate it and comment on it.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in ArtworkDetailsView.fxml)
// ----------------------------------------------------------------------
// - artworkImageView   : ImageView  (big image)
// - titleLabel         : Label
// - artistLabel        : Label
// - categoryLabel      : Label
// - descriptionArea    : TextArea   (not editable, wrap text)
// - likeButton         : Button  or ToggleButton ("Like" / "Unlike")
// - likesCountLabel    : Label      ("12 likes")
// - ratingSlider       : Slider     (min 1, max 5, step 1, snap to ticks)
// - ratingValueLabel   : Label      (shows the slider value: "4 stars")
// - submitRatingButton : Button
// - averageRatingLabel : Label      ("Average: 4.2 / 5 (10 ratings)")
// - commentsListView   : ListView<Comment>   <-- required ListView (R1)
// - commentArea        : TextArea   (write a new comment)
// - charCountLabel     : Label      ("25 / 200")
// - addCommentButton   : Button
// - backButton         : Button
// - messageLabel       : Label
//
// WHEN THE SCREEN OPENS  (initialize method)
// ------------------------------------------
// 1. Get the selected artwork from UserSession.getSelectedArtwork().
// 2. Fill the labels, image and description.
// 3. Load its comments into commentsListView.
// 4. Show number of likes and average rating.
// 5. If the current user already liked it, show "Unlike".
// 6. If the current user already rated it, put his old value on the slider.
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Action event: likeButton         -> like / unlike, then update count.
// - Action event: submitRatingButton -> save rating, update average.
// - Action event: addCommentButton   -> add comment to the list.
// - Action event: backButton         -> back to GalleryView.fxml
// - Binding: ratingValueLabel.textProperty() bound to the slider value
//   (or a ChangeListener on ratingSlider.valueProperty()).  <-- property event
// - ChangeListener on commentArea.textProperty() -> update charCountLabel.
// - Binding: addCommentButton disabled while commentArea is empty.
// - (Optional) Mouse event: double-click on the image to like it.
//
// INPUT VALIDATION (R6)
// ---------------------
// - Comment must not be empty or only spaces.
// - Comment max 200 characters.
// - Comment must not contain "|" (file separator).
// - Rating must be between 1 and 5.
// - Show "Comment added successfully" / "Rating saved" in green, and
//   errors in red (messageLabel) or with AlertHelper.
//
// NAVIGATION
// ----------
// - Back -> SceneNavigator.switchTo("GalleryView.fxml")
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// - CommentService.getCommentsForArtwork(id) / addComment(...)
// - LikeService.toggleLike(username, artworkId) / countLikes(artworkId)
//   / hasLiked(username, artworkId)
// - RatingService.rateArtwork(username, artworkId, stars)
//   / getAverageRating(artworkId) / getUserRating(...)
// - Every change is saved to the data files immediately by the
//   services (R4).
// =====================================================================
