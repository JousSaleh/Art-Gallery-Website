package com.artgallery.model;

// The only two categories in the gallery.
public enum ArtworkCategory {
    OIL("Oil Paintings"),
    PASTEL("Pastel Paintings");

    private final String displayName;

    ArtworkCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}