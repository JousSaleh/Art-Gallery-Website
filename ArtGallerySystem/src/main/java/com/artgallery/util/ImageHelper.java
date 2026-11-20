// =====================================================================
// File: ImageHelper.java
// Package: com.artgallery.util
// Role: Shared helper - loading artwork images safely  -  R1, R6
// =====================================================================
//
// PURPOSE
// -------
// Loads an artwork image from the resources/images folder using the
// imagePath stored in the Artwork object (e.g. "oil/sunset.jpg").
//
// WHAT TO IMPLEMENT LATER
// -----------------------
// - IMAGES_FOLDER constant = "/com/artgallery/images/"
// - loadImage(String imagePath) -> Image
//     1. Build the full path: IMAGES_FOLDER + imagePath
//     2. Use getClass().getResource(...) / getResourceAsStream(...)
//        to create a javafx.scene.image.Image.
//     3. If the file does not exist (resource is null), return a default
//        image from images/app/ (for example "no_image.png" that you add
//        yourself) or return null and let the controller show a Label
//        "Image not found". Never crash.  (R6)
//
// NOTE
// ----
// This is a GUI helper (it uses javafx Image), so it lives in util and
// is used only by controllers - NOT by model classes.
// =====================================================================
