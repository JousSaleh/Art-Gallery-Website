package com.artgallery.repository;

import com.artgallery.model.Artwork;
import com.artgallery.model.ArtworkCategory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Reads data/artworks.txt (written by hand by the developer).
// Line format: id|title|artist|description|category|imagePath|year
public class ArtworkRepository {

    private static final String FILE_PATH = "data/artworks.txt";

    public List<Artwork> loadAll() {
        List<Artwork> artworks = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            System.out.println("File not found: " + path.toAbsolutePath());
            return artworks;
        }

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            int lineNumber = 0;

            for (String line : lines) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;   // skip empty lines
                }
                try {
                    artworks.add(parseLine(line));
                } catch (IllegalArgumentException e) {
                    // wrong field count, wrong category, or year is not a number
                    System.out.println("Skipped line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read " + FILE_PATH + ": " + e.getMessage());
        }

        return artworks;
    }

    private Artwork parseLine(String line) {
        String[] parts = line.split("\\|");   // "|" must be escaped in split

        if (parts.length != 7) {
            throw new IllegalArgumentException("expected 7 fields but found " + parts.length);
        }

        String id = parts[0].trim();
        String title = parts[1].trim();
        String artist = parts[2].trim();
        String description = parts[3].trim();
        ArtworkCategory category = ArtworkCategory.valueOf(parts[4].trim().toUpperCase());
        String imagePath = parts[5].trim();
        int year = Integer.parseInt(parts[6].trim());

        return new Artwork(id, title, artist, description, category, imagePath, year);
    }
}