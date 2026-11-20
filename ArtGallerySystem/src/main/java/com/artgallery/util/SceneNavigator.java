// =====================================================================
// File: SceneNavigator.java
// Package: com.artgallery.util
// Role: Switching between FXML screens  -  Requirement R2 (navigation)
// =====================================================================
//
// PURPOSE
// -------
// One place that knows how to load an FXML file and show it in the main
// window. Every controller calls it from an event handler, for example
// inside a button's action event (R2: "each window is opened from an
// event handler").
//
// WHAT TO IMPLEMENT LATER
// -----------------------
// - A static field that stores the main Stage (set once from Main.start).
// - setStage(Stage stage)
// - switchTo(String fxmlFileName)
//     1. Use FXMLLoader to load "/com/artgallery/view/" + fxmlFileName.
//     2. Create a new Scene with the loaded root.
//     3. Add the stylesheet "/com/artgallery/css/style.css" to the scene.
//     4. stage.setScene(scene) and update the title if you want.
//
// ERROR HANDLING (R6)
// -------------------
// - Catch IOException (file not found / FXML error) and show it with
//   AlertHelper.showError("Could not open screen: " + fxmlFileName).
//
// NOTE
// ----
// To pass data between screens (e.g. which artwork was clicked) we use
// UserSession instead of passing objects to the next controller. This is
// simpler for beginners.
// =====================================================================
