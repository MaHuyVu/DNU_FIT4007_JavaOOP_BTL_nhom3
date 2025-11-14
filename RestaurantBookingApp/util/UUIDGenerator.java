package util;

import java.util.UUID;

public class UUIDGenerator {
    public static String generate() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
