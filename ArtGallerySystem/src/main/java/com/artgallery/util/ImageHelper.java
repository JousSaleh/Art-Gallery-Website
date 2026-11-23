package com.artgallery.util;

import javafx.scene.image.Image;

import java.io.InputStream;

// Loads artwork images from resources/com/artgallery/images/
public class ImageHelper {

    private static final String IMAGES_FOLDER = "/com/artgallery/images/";

    // Returns null if the image is missing (the caller shows a text instead).
    public static Image loadImage(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return null;
        }

        InputStream stream = ImageHelper.class.getResourceAsStream(IMAGES_FOLDER + imagePath);
        if (stream == null) {
            System.out.println("Image not found: " + IMAGES_FOLDER + imagePath);
            return null;
        }
        return new Image(stream);
    }
}