// =====================================================================
// File: FileManager.java
// Package: com.artgallery.repository
// Layer: PERSISTENCE  -  Requirements R4, R6
// =====================================================================
//
// PURPOSE
// -------
// A small shared helper used by ALL repositories to read and write
// plain text files in the data/ folder. Writing this once avoids
// repeating the same file code five times.
//
// No javafx imports here. Controllers must NEVER call this class
// directly; only repositories use it.
//
// OPERATIONS TO IMPLEMENT LATER
// -----------------------------
// - readLines(String fileName)
//     Open "data/" + fileName and return all lines as a List<String>.
//     - If the file does not exist -> create it and return an empty list.
//     - Skip empty lines.
//     - You can use Scanner, BufferedReader, or Files.readAllLines(...).
//
// - writeLines(String fileName, List<String> lines)
//     Overwrite the file with the given lines (one record per line).
//     You can use PrintWriter or Files.write(...).
//
// - DATA_FOLDER constant = "data/"
// - SEPARATOR  constant = "|"
//     (When splitting, remember that "|" is special in regex, so use
//      line.split("\\|") .)
//
// ERROR HANDLING (R6)
// -------------------
// - Surround all file operations with try / catch (IOException).
// - Do not let the program crash; print the error and/or let the caller
//   show a friendly message with AlertHelper.
// =====================================================================
