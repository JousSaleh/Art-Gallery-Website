package com.artgallery.service;

import com.artgallery.model.Comment;
import com.artgallery.repository.CommentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Business logic for comments (Streams - R5).
public class CommentService {

    public static final int MAX_LENGTH = 200;

    private static final CommentRepository repository = new CommentRepository();
    private static List<Comment> comments = new ArrayList<>();

    // Called once when the application starts.
    public static void loadComments() {
        comments = repository.loadAll();
    }

    // Adds a comment and saves the file immediately (R4).
    // Throws IllegalArgumentException if the text is not valid (R6).
    public static Comment addComment(String username, String artworkId, String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty.");
        }
        if (text.trim().length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Comment must be at most " + MAX_LENGTH + " characters.");
        }
        if (text.contains("|")) {
            throw new IllegalArgumentException("Comment cannot contain the character |");
        }

        String id = "C" + (comments.size() + 1);
        String today = LocalDate.now().toString();
        String cleanText = text.trim().replace("\n", " ");   // keep one line in the file

        Comment comment = new Comment(id, username, artworkId, today, cleanText);
        comments.add(comment);
        repository.saveAll(comments);
        return comment;
    }

    // Comments of one artwork, newest first (filter + sorted)
    public static List<Comment> getCommentsForArtwork(String artworkId) {
        return comments.stream()
                .filter(c -> c.getArtworkId().equals(artworkId))
                .sorted(Comparator.comparing(Comment::getDate).reversed())
                .collect(Collectors.toList());
    }

    // Comments written by one user (for the My Activity screen)
    public static List<Comment> getCommentsByUser(String username) {
        return comments.stream()
                .filter(c -> c.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public static long countCommentsForArtwork(String artworkId) {
        return comments.stream()
                .filter(c -> c.getArtworkId().equals(artworkId))
                .count();
    }

    // artworkId -> number of comments (groupingBy + counting, for Statistics)
    public static Map<String, Long> countCommentsPerArtwork() {
        return comments.stream()
                .collect(Collectors.groupingBy(Comment::getArtworkId, Collectors.counting()));
    }

    public static List<Comment> getAllComments() {
        return new ArrayList<>(comments);
    }
}