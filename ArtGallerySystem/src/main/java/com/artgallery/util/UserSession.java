// =====================================================================
// File: UserSession.java
// Package: com.artgallery.util
// Role: Shared helper - remembers data between screens
// =====================================================================
//
// PURPOSE
// -------
// Remembers WHO is logged in and WHICH artwork was selected, so any
// controller can read them after a scene change.
//
// WHAT TO IMPLEMENT LATER (static fields + static methods)
// --------------------------------------------------------
// - currentUser      : User     -> set after a successful login
// - selectedArtwork  : Artwork  -> set when a card is clicked in the gallery
//
// - setCurrentUser(User user) / getCurrentUser()
// - setSelectedArtwork(Artwork a) / getSelectedArtwork()
// - isLoggedIn()  -> currentUser != null
// - clear()       -> set both to null (called on logout)
//
// USED BY
// -------
// LoginController (sets the user), GalleryController (sets the artwork),
// ArtworkDetailsController, MyActivityController (read them).
// =====================================================================
