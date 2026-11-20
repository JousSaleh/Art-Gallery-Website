// =====================================================================
// File: StatisticsController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R1, R2, R3, R5
// Paired view: resources/com/artgallery/view/StatisticsView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Shows a simple statistics report about the gallery.
// This screen is the main place to show the "report with Collectors"
// required in R5.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in StatisticsView.fxml)
// ------------------------------------------------------------------
// - totalArtworksLabel   : Label  ("Total artworks: 10")
// - oilCountLabel        : Label  ("Oil Paintings: 6")
// - pastelCountLabel     : Label  ("Pastel Paintings: 4")
// - totalUsersLabel      : Label  (optional)
// - mostLikedListView    : ListView<String>  (top 5 most-liked artworks)
// - topRatedListView     : ListView<String>  (artworks with highest average rating)
// - categoryAverageLabel : Label  (average rating per category, optional)
// - refreshButton        : Button
// - backButton           : Button
//
// WHEN THE SCREEN OPENS
// ---------------------
// Call StatisticsService methods and put the results in the labels
// and list views.
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Action event: refreshButton -> recalculate statistics.
// - Action event: backButton    -> GalleryView.fxml
//
// FUNCTIONAL PROGRAMMING (R5) - done in StatisticsService
// -------------------------------------------------------
// - Collectors.groupingBy(Artwork::getCategory, Collectors.counting())
//   -> number of artworks per category.
// - sorted(...) + limit(5) -> top 5 most-liked.
// - map(...) -> convert results to display Strings.
//
// INPUT VALIDATION / ERRORS (R6)
// ------------------------------
// - If there are no likes / ratings yet, show "No data yet" instead of
//   0 or an error. Avoid dividing by zero when calculating averages.
//
// NAVIGATION
// ----------
// - Back -> "GalleryView.fxml"
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// - StatisticsService only (it uses the other services internally).
// =====================================================================
