package util;

import java.util.UUID;

public class UUIDGenerator {

    public static String generate() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateShort() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public static String generateWithPrefix(String prefix) {
        return prefix + "-" + generate();
    }
}