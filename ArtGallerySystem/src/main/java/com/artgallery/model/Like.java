
package com.artgallery.model;

public class Like {

    private String username;
    private String artworkId;

    public Like(String username, String artworkId) {
        this.username = username;
        this.artworkId = artworkId;
    }

    public String getUsername() {
        return username;
    }

    public String getArtworkId() {
        return artworkId;
    }
}

