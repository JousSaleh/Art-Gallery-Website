package com.artgallery.model;

// POJO: carries artwork data only (no JavaFX, no files).
public class Artwork {
    private String id;
    private String title;
    private String artist;
    private String description;
    private ArtworkCategory category;
    private String imagePath;   // e.g. "oil/sunset.jpg"
    private int year;

    public Artwork(String id, String title, String artist, String description,
                   ArtworkCategory category, String imagePath, int year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.category = category;
        this.imagePath = imagePath;
        this.year = year;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getDescription() { return description; }
    public ArtworkCategory getCategory() { return category; }
    public String getImagePath() { return imagePath; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return title;   // used when an Artwork is shown in a ListView
    }
}