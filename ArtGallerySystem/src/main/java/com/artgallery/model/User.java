// =====================================================================
// File: User.java
// Package: com.artgallery.model
// Layer: MODEL (MVC)  -  Requirement R3
// =====================================================================
//
// PURPOSE
// -------
// Represents ONE registered user of the application.
// Simple POJO, no GUI code, no file code.
//
// ATTRIBUTES TO ADD LATER (all private)
// -------------------------------------
// - username : String -> unique, used to log in and to link the user
//                        to his comments, likes and ratings
// - password : String -> the user's password (plain text is acceptable
//                        for this course project; mention it in the report)
// - fullName : String -> name shown in the UI, e.g. "Welcome, Sara"
// - email    : String -> used only for validation practice (R6)
//
// WHAT TO WRITE LATER
// -------------------
// 1. Constructor with all attributes.
// 2. Getters (and setters if needed).
// 3. toString() -> return the username or full name.
//
// RELATIONSHIPS
// -------------
// - One User can write MANY Comments.
// - One User can give MANY Likes (but only one like per artwork).
// - One User can give MANY Ratings (but only one rating per artwork).
// The link is the "username" stored inside Comment, Like and Rating.
//
// NOTE
// ----
// There is NO admin user. Users can only interact with artworks
// (comment / like / rate). They cannot upload or manage images.
// =====================================================================
