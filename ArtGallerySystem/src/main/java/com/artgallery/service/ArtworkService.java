package com.artgallery.service;

import com.artgallery.model.Artwork;
import com.artgallery.model.ArtworkCategory;
import com.artgallery.repository.ArtworkRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Business logic for artworks: get, search, filter, sort (Streams - R5).
public class ArtworkService {

    public static final String SORT_TITLE_AZ = "Title (A-Z)";
    public static final String SORT_TITLE_ZA = "Title (Z-A)";
    public static final String SORT_NEWEST   = "Newest first";
    public static final String SORT_OLDEST   = "Oldest first";

    private static final ArtworkRepository repository = new ArtworkRepository();
    private static List<Artwork> artworks = new ArrayList<>();

    // Called once when the application starts.
    public static void loadArtworks() {
        artworks = repository.loadAll();
    }

    public static List<Artwork> getAllArtworks() {
        return new ArrayList<>(artworks);
    }

    public static Artwork findById(String id) {
        return artworks.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Artwork> getByCategory(ArtworkCategory category) {
        return artworks.stream()
                .filter(a -> a.getCategory() == category)
                .collect(Collectors.toList());
    }

    // category == null means "All"
    public static List<Artwork> search(String text, ArtworkCategory category) {
        String keyword = (text == null) ? "" : text.trim().toLowerCase();

        // Built-in functional interface: Predicate (R5)
        Predicate<Artwork> matchesText = a ->
                a.getTitle().toLowerCase().contains(keyword)
                || a.getArtist().toLowerCase().contains(keyword);

        Predicate<Artwork> matchesCategory = a ->
                category == null || a.getCategory() == category;

        return artworks.stream()
                .filter(matchesText.and(matchesCategory))
                .collect(Collectors.toList());
    }

    public static List<Artwork> sort(List<Artwork> list, String option) {
        if (option == null) {
            option = SORT_TITLE_AZ;
        }

        Comparator<Artwork> comparator;
        switch (option) {
            case SORT_TITLE_ZA:
                comparator = Comparator.comparing(Artwork::getTitle, String.CASE_INSENSITIVE_ORDER).reversed();
                break;
            case SORT_NEWEST:
                comparator = Comparator.comparingInt(Artwork::getYear).reversed();
                break;
            case SORT_OLDEST:
                comparator = Comparator.comparingInt(Artwork::getYear);
                break;
            default:
                comparator = Comparator.comparing(Artwork::getTitle, String.CASE_INSENSITIVE_ORDER);
        }

        return list.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    // map example (R5)
    public static List<String> getTitles() {
        return artworks.stream()
                .map(Artwork::getTitle)
                .collect(Collectors.toList());
    }
}