// =====================================================================
// File: ArtworkService.java
// Package: com.artgallery.service
// Layer: SERVICE (business logic between controllers and repositories)
// Requirements: R3, R5
// =====================================================================
//
// PURPOSE
// -------
// Holds the list of artworks in memory and gives the controllers simple
// methods to get, search, filter and sort artworks.
//
// No javafx imports here (keep it independent of the GUI).
//
// DATA IT KEEPS (later)
// ---------------------
// - A List<Artwork> filled once at start-up from ArtworkRepository.
//   (Tip: make the service "static" methods or a single shared object so
//    every controller sees the same list.)
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadArtworks()
//     Ask ArtworkRepository to read data/artworks.txt.
// - getAllArtworks()
//     Return the full list.
// - findById(String id)
//     Return the artwork with this id. Use a Stream:
//     filter(a -> a.getId().equals(id)).findFirst()             (R5)
// - getByCategory(ArtworkCategory category)
//     filter(a -> a.getCategory() == category)                  (R5)
// - search(String text, ArtworkCategory category)
//     Build a Predicate<Artwork> that checks the title OR artist contains
//     the text (ignore case), combine it with the category Predicate
//     using .and(...), then filter with it.     <-- built-in functional
//                                                   interface Predicate (R5)
//     If category is null -> means "All".
// - sortByTitle(List<Artwork> list)
//     sorted(Comparator.comparing(Artwork::getTitle))  <-- method reference (R5)
// - getTitles()  (optional)
//     map(Artwork::getTitle).collect(Collectors.toList())        (R5)
//
// BUSINESS RULES
// --------------
// - There is NO addArtwork / deleteArtwork method for users.
//   Artworks are added only by the developer editing artworks.txt and
//   putting the image in the images folder.
// =====================================================================
