// =====================================================================
// File: ArtworkCategory.java
// Package: com.artgallery.model
// Layer: MODEL (MVC)  -  Requirement R3
// =====================================================================
//
// PURPOSE
// -------
// Defines the ONLY two categories allowed in the Art Gallery System.
//
// WHAT TO WRITE LATER
// -------------------
// Write this as a Java "enum" with exactly two values:
//
//     OIL      -> Oil Paintings
//     PASTEL   -> Pastel Paintings
//
// Optional (nice to have):
// - Give each value a display name ("Oil Paintings", "Pastel Paintings")
//   with a getter, and return it from toString(), so it looks nice inside
//   a ComboBox, RadioButton text or ListView.
//
// WHERE IT IS USED
// ----------------
// - Artwork.java            -> every artwork has one category.
// - GalleryController       -> filter between All / Oil / Pastel.
// - StatisticsService       -> count artworks per category using
//                              Collectors.groupingBy(...)  (R5).
// - artworks.txt            -> the category is saved as the text
//                              "OIL" or "PASTEL" and converted back
//                              with ArtworkCategory.valueOf(...).
//
// RULE
// ----
// Do NOT add more categories. The project must have exactly two.
// No javafx imports in this file.
// =====================================================================
