
package com.artgallery.repository;

import com.artgallery.model.Rating;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class RatingRepository {

    private static final Path FILE_PATH =
            Paths.get("data", "ratings.txt");

    public List<Rating> loadAll() {
        List<Rating> ratings = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return ratings;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line : lines) {
                try {
                    String[] parts = line.split("\\|", -1);

                    if (parts.length != 3
                            || parts[0].isBlank()
                            || parts[1].isBlank()
                            || parts[2].isBlank()) {
                        continue;
                    }

                    int stars = Integer.parseInt(parts[2].trim());

                    if (stars < 1 || stars > 5) {
                        continue;
                    }

                    ratings.add(new Rating(
                            parts[0].trim(),
                            parts[1].trim(),
                            stars
                    ));

                } catch (NumberFormatException e) {
                    // Skip invalid rating numbers
                } catch (RuntimeException e) {
                    // Skip broken lines
                }
            }

        } catch (IOException e) {
            System.err.println(
                    "Error loading ratings: " + e.getMessage()
            );
        }

        return ratings;
    }

    public void saveAll(List<Rating> ratings) {
        try {
            Files.createDirectories(FILE_PATH.getParent());

            List<String> lines = new ArrayList<>();

            for (Rating rating : ratings) {
                if (rating != null
                        && rating.getUsername() != null
                        && rating.getArtworkId() != null
                        && rating.getStars() >= 1
                        && rating.getStars() <= 5) {

                    lines.add(
                            rating.getUsername() + "|"
                            + rating.getArtworkId() + "|"
                            + rating.getStars()
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
                    "Error saving ratings: " + e.getMessage()
            );
        }
    }
}

