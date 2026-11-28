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