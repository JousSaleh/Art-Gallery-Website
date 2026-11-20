// =====================================================================
// File: StatisticsService.java
// Package: com.artgallery.service
// Layer: SERVICE  -  Requirement R5 (Stream API + Collectors report)
// =====================================================================
//
// PURPOSE
// -------
// Calculates the numbers shown on the Gallery Statistics screen.
// It does NOT keep its own data; it asks the other services for their
// lists and processes them with Streams. No javafx imports here.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - countArtworksPerCategory()
//     Return a Map<ArtworkCategory, Long> using:
//       artworks.stream()
//               .collect(Collectors.groupingBy(Artwork::getCategory,
//                                              Collectors.counting()));
//     <-- the required "report with Collectors" (R5)
//
// - getMostLikedArtworks(int limit)
//     Sort artworks by LikeService.countLikes(id) (highest first),
//     limit(limit), and map each one to a String like
//     "Sunset - 12 likes".                         (sorted + map, R5)
//
// - getTopRatedArtworks(int limit)
//     Sort by RatingService.getAverageRating(id) (highest first),
//     limit(limit), map to "Sunset - 4.5 / 5".
//
// - getAverageRatingPerCategory()   (optional, extra Collectors practice)
//     Use Collectors.groupingBy(category, Collectors.averagingDouble(...)).
//
// - getTotalArtworks(), getTotalLikes(), getTotalComments()  (simple counts)
//
// NOTES
// -----
// - Make sure nothing crashes when lists are empty (return empty maps /
//   lists, and 0 for averages).
// =====================================================================
