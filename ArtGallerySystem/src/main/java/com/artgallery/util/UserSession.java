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
package com.artgallery.util;

import com.artgallery.model.User;
import com.artgallery.model.Artwork;

public final class UserSession {

    private static User currentUser;
    private static Artwork selectedArtwork;

    private UserSession() {
        // Utility class: no objects needed
    }

    // Store the logged-in user
    public static void setCurrentUser(User user) {
        currentUser = user;
        selectedArtwork = null;
    }

    // Get the logged-in user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Check whether someone is logged in
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // Store the selected artwork
    public static void setSelectedArtwork(Artwork artwork) {
        selectedArtwork = artwork;
    }

    // Get the selected artwork
    public static Artwork getSelectedArtwork() {
        return selectedArtwork;
    }

    // Clear session data on logout
    public static void clear() {
        currentUser = null;
        selectedArtwork = null;
    }
}