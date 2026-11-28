
package com.artgallery.util;

import java.util.function.Predicate;

public class InputValidator {

    private static final Predicate<String> NOT_EMPTY =
            s -> s != null && !s.trim().isEmpty();

    private static final Predicate<String> HAS_AT =
            s -> s.contains("@");

    private static final Predicate<String> HAS_DOT =
            s -> s.contains(".");

    private static final Predicate<String> VALID_EMAIL =
            NOT_EMPTY.and(HAS_AT).and(HAS_DOT);

    public static boolean isEmpty(String text) {
        return !NOT_EMPTY.test(text);
    }

    public static boolean isSafeForStorage(String text) {
        return text != null
                && !text.contains("|")
                && !text.contains("\n")
                && !text.contains("\r");
    }

    public static boolean isValidUsername(String username) {
        return !isEmpty(username)
                && username.length() >= 3
                && username.chars().noneMatch(Character::isWhitespace)
                && isSafeForStorage(username);
    }

    public static boolean isValidEmail(String email) {
        return VALID_EMAIL.test(email)
                && isSafeForStorage(email);
    }

    public static boolean isValidPassword(String password) {
        return !isEmpty(password)
                && password.length() >= 6
                && isSafeForStorage(password);
    }

    public static boolean passwordsMatch(String p1, String p2) {
        return p1 != null && p1.equals(p2);
    }

    public static boolean isValidComment(String text) {
        return !isEmpty(text)
                && text.length() <= 200
                && isSafeForStorage(text);
    }

    public static boolean isValidRating(int stars) {
        return stars >= 1 && stars <= 5;
    }
}