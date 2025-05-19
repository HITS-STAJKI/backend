package ru.hits.internship.user.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class PasswordGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate() {
        return generate(10);
    }

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_POOL.charAt(RANDOM.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }

    public static String generateBasedOn(String base) {
        String hash = UUID.nameUUIDFromBytes(base.getBytes()).toString().replaceAll("-", "");
        return hash.substring(0, 10);
    }
}
