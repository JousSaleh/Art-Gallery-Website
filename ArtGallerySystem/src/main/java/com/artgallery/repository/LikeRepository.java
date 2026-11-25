package com.artgallery.repository;

import com.artgallery.model.Like;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class LikeRepository {

    private static final Path FILE_PATH =
            Paths.get("data", "likes.txt");

    public List<Like> loadAll() {
        List<Like> likes = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return likes;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line : lines) {
                try {
                    String[] parts = line.split("\\|", -1);

                    if (parts.length != 2
                            || parts[0].isBlank()
                            || parts[1].isBlank()) {
                        continue;
                    }

                    likes.add(new Like(
                            parts[0].trim(),
                            parts[1].trim()
                    ));

                } catch (RuntimeException e) {
                    // Skip invalid line
                }
            }

        } catch (IOException e) {
            System.err.println(
                    "Error loading likes: " + e.getMessage()
            );
        }

        return likes;
    }

    public void saveAll(List<Like> likes) {
        try {
            Files.createDirectories(FILE_PATH.getParent());

            List<String> lines = new ArrayList<>();

            for (Like like : likes) {
                if (like != null
                        && like.getUsername() != null
                        && like.getArtworkId() != null) {

                    lines.add(
                            like.getUsername() + "|"
                            + like.getArtworkId()
                    );
                }
            }

            Files.write(
                    FILE_PATH,
                    lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            System.err.println(
                    "Error saving likes: " + e.getMessage()
            );
        }
    }
}

