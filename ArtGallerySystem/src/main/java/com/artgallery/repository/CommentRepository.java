package com.artgallery.repository;

import com.artgallery.model.Comment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Loads and saves data/comments.txt
// Line format: commentId|username|artworkId|date|text
public class CommentRepository {

    private static final String FILE_PATH = "data/comments.txt";

    public List<Comment> loadAll() {
        List<Comment> comments = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            return comments;   // no comments yet
        }

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            int lineNumber = 0;

            for (String line : lines) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    comments.add(parseLine(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipped comment line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read " + FILE_PATH + ": " + e.getMessage());
        }

        return comments;
    }

    public void saveAll(List<Comment> comments) {
        // Convert every Comment to one line (map - R5)
        List<String> lines = comments.stream()
                .map(c -> c.getId() + "|" + c.getUsername() + "|" + c.getArtworkId()
                        + "|" + c.getDate() + "|" + c.getText())
                .collect(Collectors.toList());

        try {
            Path path = Paths.get(FILE_PATH);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());   // create data/ if missing
            }
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not save " + FILE_PATH + ": " + e.getMessage());
        }
    }

    private Comment parseLine(String line) {
        String[] parts = line.split("\\|", 5);   // 5 = text is always the last part

        if (parts.length != 5) {
            throw new IllegalArgumentException("expected 5 fields but found " + parts.length);
        }

        return new Comment(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                           parts[3].trim(), parts[4].trim());
    }
}