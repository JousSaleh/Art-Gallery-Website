// =====================================================================
// File: InputValidator.java
// Package: com.artgallery.util
// Role: Input validation helpers  -  Requirements R5, R6
// =====================================================================
//
// PURPOSE
// -------
// Collects all input checks in one place so every controller validates
// input in the same way. No javafx imports needed.
//
// CHECKS TO IMPLEMENT LATER (static methods returning boolean)
// ------------------------------------------------------------
// - isEmpty(String text)
//     true if text is null or only spaces (text.trim().isEmpty()).
// - isValidUsername(String username)
//     at least 3 characters, no spaces, no "|".
// - isValidEmail(String email)
//     contains "@" and "." (simple check is enough for this course).
// - isValidPassword(String password)
//     at least 6 characters.
// - passwordsMatch(String p1, String p2)
// - isValidComment(String text)
//     not empty, max 200 characters, does not contain "|".
// - isValidRating(int stars)
//     between 1 and 5.
//
// FUNCTIONAL PROGRAMMING IDEA (R5)
// --------------------------------
// You can write some checks as built-in functional interfaces, e.g.
//     Predicate<String> NOT_EMPTY  = s -> s != null && !s.trim().isEmpty();
//     Predicate<String> HAS_AT     = s -> s.contains("@");
//     Predicate<String> VALID_EMAIL = NOT_EMPTY.and(HAS_AT);
// and call VALID_EMAIL.test(email). This satisfies "at least one
// built-in functional interface" (Predicate).
//
// MESSAGES
// --------
// Optionally return a clear error message String instead of a boolean,
// e.g. "Email format is not valid.", so the controller can show it.
// =====================================================================
