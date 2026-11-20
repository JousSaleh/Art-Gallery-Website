// =====================================================================
// File: WelcomeController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R2, R3
// Paired view: resources/com/artgallery/view/WelcomeView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Controls the first screen. It introduces the Art Gallery System and
// lets the user go to Login or Registration.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in WelcomeView.fxml)
// ---------------------------------------------------------------
// - loginButton    : Button
// - registerButton : Button
// - exitButton     : Button
// - logoImageView  : ImageView  (optional, gallery logo from images/app/)
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Action event: loginButton    -> go to LoginView.fxml
// - Action event: registerButton -> go to RegisterView.fxml
// - Action event: exitButton     -> close the application
//   (Platform.exit()), optionally after a confirmation Alert.
//
// TIP (R5): you can register these handlers inside initialize() with
// lambda expressions, e.g. loginButton.setOnAction(e -> ...),
// OR use onAction="#handleLogin" in the FXML. Pick ONE style and use it
// consistently in the whole project (no duplicated handlers).
//
// INPUT VALIDATION
// ----------------
// None on this screen (there are no input fields).
//
// NAVIGATION
// ----------
// Uses SceneNavigator.switchTo("LoginView.fxml") /
// SceneNavigator.switchTo("RegisterView.fxml").
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// None. This screen does not read or change any data.
// =====================================================================
