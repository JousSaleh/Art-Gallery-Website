package com.artgallery.model;

// POJO: one comment written by a user on an artwork.
public class Comment {
    private String id;
    private String username;
    private String artworkId;
    private String date;   // yyyy-MM-dd
    private String text;

    public Comment(String id, String username, String artworkId, String date, String text) {
        this.id = id;
        this.username = username;
        this.artworkId = artworkId;
        this.date = date;
        this.text = text;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getArtworkId() { return artworkId; }
    public String getDate() { return date; }
    public String getText() { return text; }

    @Override
    public String toString() {
        return username + " (" + date + "): " + text;   // how it looks in a ListView
    }
}