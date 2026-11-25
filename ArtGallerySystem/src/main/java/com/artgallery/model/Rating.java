
package com.artgallery.model;

public class Rating {

    private String username;
    private String artworkId;
    private int stars;

    public Rating(String username, String artworkId, int stars) {
        this.username = username;
        this.artworkId = artworkId;
        this.stars = stars;
    }

    public String getUsername() {
        return username;
    }

    public String getArtworkId() {
        return artworkId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}

