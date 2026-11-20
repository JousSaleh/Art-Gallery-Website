// =====================================================================
// File: UserService.java
// Package: com.artgallery.service
// Layer: SERVICE  -  Requirements R3, R4, R5, R6
// =====================================================================
//
// PURPOSE
// -------
// Handles user accounts: registration and login.
// No javafx imports here.
//
// DATA IT KEEPS (later)
// ---------------------
// - A List<User> loaded from UserRepository at start-up.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadUsers()
//     Read data/users.txt through UserRepository.
// - usernameExists(String username)
//     Stream: anyMatch(u -> u.getUsername().equalsIgnoreCase(username)) (R5)
// - register(User newUser)
//     1. If the username already exists -> throw an exception or return
//        false so the controller can show "Username already taken" (R6).
//     2. Add the user to the list.
//     3. Save the list with UserRepository.saveAll(...)          (R4)
// - login(String username, String password)
//     Stream: filter by username AND password, findFirst().
//     Return the User (or Optional<User>). Return empty if wrong.
// - countUsers()  (optional, for statistics)
//
// BUSINESS RULES
// --------------
// - Usernames are unique.
// - Every successful registration is saved immediately to users.txt.
// =====================================================================
