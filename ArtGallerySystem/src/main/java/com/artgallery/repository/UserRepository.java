// =====================================================================
// File: UserRepository.java
// Package: com.artgallery.repository
// Layer: PERSISTENCE  -  Requirement R4
// Data file: data/users.txt
// =====================================================================
//
// PURPOSE
// -------
// Loads users from data/users.txt and saves them back after a new
// registration.
//
// LINE FORMAT
// -----------
//   username|password|fullName|email
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - loadAll()
//     Read lines with FileManager, split each by "|", create User objects,
//     return List<User>.
// - saveAll(List<User> users)
//     Convert each User to one line (map(u -> u.getUsername() + "|" + ...))
//     and call FileManager.writeLines("users.txt", lines).
//
// ERROR HANDLING (R6)
// -------------------
// - Skip broken lines instead of crashing.
// =====================================================================
