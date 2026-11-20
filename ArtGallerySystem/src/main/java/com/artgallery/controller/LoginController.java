// =====================================================================
// File: LoginController.java
// Package: com.artgallery.controller
// Layer: CONTROLLER (MVC)  -  Requirements R2, R3, R5, R6
// Paired view: resources/com/artgallery/view/LoginView.fxml
// =====================================================================
//
// PURPOSE
// -------
// Lets an existing user log in with username and password.
//
// @FXML FIELDS TO ADD LATER (must match fx:id in LoginView.fxml)
// -------------------------------------------------------------
// - usernameField : TextField
// - passwordField : PasswordField
// - loginButton   : Button
// - backButton    : Button
// - registerLink  : Hyperlink or Button  ("Don't have an account?")
// - messageLabel  : Label  (shows error messages in red)
//
// EVENTS IT WILL HANDLE (R2)
// --------------------------
// - Action event:  loginButton  -> try to log in.
// - Key event:     pressing ENTER inside passwordField also logs in
//                  (setOnKeyPressed + check KeyCode.ENTER).  <-- keyboard event
// - Action event:  backButton   -> back to WelcomeView.fxml
// - Action event:  registerLink -> go to RegisterView.fxml
// - Property binding: disable loginButton while username OR password is
//   empty, e.g. bind disableProperty() to
//   usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty())
//   (Lecture 7 - Binding Property)
//
// INPUT VALIDATION (R6)
// ---------------------
// - Username and password must not be empty (InputValidator.isEmpty).
// - If the username/password are wrong, show
//   "Invalid username or password." in messageLabel.
//
// NAVIGATION
// ----------
// - On success: save the logged-in user in UserSession, then
//   SceneNavigator.switchTo("GalleryView.fxml").
// - Back: SceneNavigator.switchTo("WelcomeView.fxml").
//
// CONNECTIONS TO MODEL / SERVICES
// -------------------------------
// - UserService.login(username, password) -> returns the User or null
//   (or Optional<User>).
// - UserSession.setCurrentUser(user).
// =====================================================================
