
package com.artgallery.service;

import com.artgallery.model.Like;
import com.artgallery.model.Artwork;
import com.artgallery.repository.LikeRepository;

import java.util.ArrayList;
import java.util.List;

public class LikeService {

    private final LikeRepository likeRepository;
    private List<Like> likes;

    public LikeService() {
        this.likeRepository = new LikeRepository();
        this.likes = new ArrayList<>();
    }

    // Load likes from file
    public void loadLikes() {
        likes = likeRepository.loadAll();
    }

    // Check if user already liked the artwork
    public boolean hasLiked(String username, String artworkId) {
        return likes.stream()
                .anyMatch(like ->
                        like.getUsername().equals(username)
                        && like.getArtworkId().equals(artworkId));
    }

    // Like or Unlike an artwork
    public boolean toggleLike(String username, String artworkId) {

        if (hasLiked(username, artworkId)) {

            likes.removeIf(like ->
                    like.getUsername().equals(username)
                    && like.getArtworkId().equals(artworkId));

            likeRepository.saveAll(likes);
            return false;

        } else {

            likes.add(new Like(username, artworkId));

            likeRepository.saveAll(likes);
            return true;
        }
    }

    // Count likes for an artwork
    public long countLikes(String artworkId) {
        return likes.stream()
                .filter(like ->
                        like.getArtworkId().equals(artworkId))
                .count();
    }

    // Get artworks liked by a specific user
    public List<Artwork> getLikedArtworks(String username) {

        return likes.stream()
                .filter(like ->
                        like.getUsername().equals(username))
                .map(like ->
                        ArtworkService.findById(like.getArtworkId()))
                .filter(artwork -> artwork != null)
                .toList();
    }
}
