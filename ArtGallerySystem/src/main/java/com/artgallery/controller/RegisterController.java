// =====================================================================
// File: RegisterController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R2, R3, R4, R6
// Paired view: resources/com/artgallery/view/RegisterView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Lets a new user create an account.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in RegisterView.fxml)
// ----------------------------------------------------------------
// - fullNameField        : TextField
// - usernameField        : TextField
// - emailField           : TextField
// - passwordField        : PasswordField
// - confirmPasswordField : PasswordField
// - termsCheckBox        : CheckBox  ("I agree to the gallery rules")
// - registerButton       : Button
// - backButton           : Button
// - messageLabel         : Label  (red for errors, green for success)
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Action event: registerButton -> validate and create the account.
// - Action event: backButton     -> back to WelcomeView.fxml
// - Property binding: registerButton is disabled until termsCheckBox
//   is selected  (registerButton.disableProperty().bind(
//   termsCheckBox.selectedProperty().not()) ).
// - (Optional) ChangeListener on passwordField that shows
//   "Password is too short" while the user is typing.
//
// INPUT VALIDATION (R6) - use InputValidator
// ------------------------------------------
// - No field may be empty.
// - Username: no spaces, no "|" character, at least 3 characters.
// - Username must NOT already exist (UserService.usernameExists) -> duplicates.
// - Email must have a valid format (contains "@" and ".").
// - Password at least 6 characters.
// - Password and Confirm Password must be equal.
// Show the FIRST error found in messageLabel or in an Alert.
//
// NAVIGATION
// ----------
// - On success: show a success Alert ("Account created successfully")
//   then SceneNavigator.switchTo("LoginView.fxml").
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// - Create a new User object (model).
// - UserService.register(user) -> adds the user and saves users.txt (R4).
// =====================================================================
