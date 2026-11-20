// =====================================================================
// File: ArtworkRepository.java
// Package: com.artgallery.repository
// Layer: PERSISTENCE  -  Requirement R4
// Data file: data/artworks.txt
// =====================================================================
//
// PURPOSE
// -------
// Reads the artworks that the DEVELOPER wrote manually in
// data/artworks.txt and turns each line into an Artwork object.
//
// LINE FORMAT (one artwork per line, fields separated by "|")
// ----------------------------------------------------------
//   id|title|artist|description|category|imagePath|year
//
//   - category  is OIL or PASTEL
//   - imagePath is relative to resources/com/artgallery/images/
//     e.g.  oil/my_painting.jpg   or   pastel/my_painting.jpg
//   (See README.md, section "Data files".)
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadAll()
//     1. lines = FileManager.readLines("artworks.txt")
//     2. For each line: split("\\|"), convert the category with
//        ArtworkCategory.valueOf(...), convert year with Integer.parseInt,
//        create a new Artwork(...).
//        (You can do this with a Stream: lines.stream().map(this::parseLine)
//         .collect(Collectors.toList())  - R5)
//     3. Return the List<Artwork>.
//
// ERROR HANDLING (R6)
// -------------------
// - If a line has the wrong number of fields, or a wrong category /
//   number, catch the exception (ArrayIndexOutOfBounds,
//   IllegalArgumentException, NumberFormatException), skip that line and
//   print which line is broken. Do not crash.
//
// NOTE
// ----
// There is NO saveAll() here on purpose: the application never changes
// artworks. Only the developer edits artworks.txt by hand.
// =====================================================================
