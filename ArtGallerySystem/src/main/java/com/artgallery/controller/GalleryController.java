// =====================================================================
// File: GalleryController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R1, R2, R3, R5
// Paired view: resources/com/artgallery/view/GalleryView.fxml
// =====================================================================
//
// PURPOSE
// -------
// The main screen after login. Shows all artworks and lets the user
// search, filter by category (All / Oil / Pastel) and sort them.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in GalleryView.fxml)
// ---------------------------------------------------------------
// - welcomeLabel      : Label        ("Welcome, <full name>")
// - searchField       : TextField    (search by title or artist)
// - allRadio          : RadioButton  ("All")
// - oilRadio          : RadioButton  ("Oil Paintings")
// - pastelRadio       : RadioButton  ("Pastel Paintings")
// - categoryGroup     : ToggleGroup  (the three radio buttons share it)
// - sortComboBox      : ComboBox<String>
//                       ("Title A-Z", "Most Liked", "Highest Rated")
// - artworkFlowPane   : FlowPane     (inside a ScrollPane; holds one small
//                                     "card" per artwork: ImageView + Label)
// - resultCountLabel  : Label        ("Showing 6 artworks")
// - myActivityButton  : Button
// - statisticsButton  : Button
// - logoutButton      : Button
//
// HOW TO BUILD AN ARTWORK CARD (later)
// ------------------------------------
// For each Artwork create a VBox with an ImageView (fixed width, keep
// ratio) and a Label with the title. Use ImageHelper to load the image.
// (Alternative if you want it simpler: use a ListView<Artwork> instead
//  of the FlowPane.)
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - ChangeListener on searchField.textProperty() -> live search while
//   typing (refresh the shown artworks).                     <-- property event
// - Listener on categoryGroup.selectedToggleProperty() -> refilter.
// - Action event on sortComboBox -> resort.
// - Mouse event: clicking (or double-clicking) an artwork card ->
//   remember the selected artwork and open ArtworkDetailsView.fxml.  <-- mouse event
// - Action events: myActivityButton, statisticsButton, logoutButton.
//
// FUNCTIONAL PROGRAMMING (R5)
// ---------------------------
// The filtering/sorting itself is done in ArtworkService with Streams:
//   filter(...) by category and search text, sorted(Comparator...).
// Event handlers here should be written as lambdas, e.g.
//   searchField.textProperty().addListener((obs, oldV, newV) -> refreshGallery());
//
// INPUT VALIDATION (R6)
// ---------------------
// - Search text is trimmed and compared ignoring upper/lower case.
// - If no artwork matches, show "No artworks found." instead of an
//   empty screen.
// - If an image file is missing, show a placeholder text instead of
//   crashing (handled in ImageHelper).
//
// NAVIGATION
// ----------
// - Card click     -> UserSession.setSelectedArtwork(artwork);
//                     SceneNavigator.switchTo("ArtworkDetailsView.fxml")
// - My Activity    -> "MyActivityView.fxml"
// - Statistics     -> "StatisticsView.fxml"
// - Logout         -> UserSession.clear(); "WelcomeView.fxml"
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// - ArtworkService.getAllArtworks()
// - ArtworkService.search(text, category)  /  sortBy(option)
// - LikeService / RatingService (only if sorting by likes or rating)
// - UserSession.getCurrentUser() for the welcome message
// =====================================================================
