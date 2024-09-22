package com.example.cinema_back_end.constants;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author tritcse00526x
 */
public class RandomString {
    // generate random UUID v4 string
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
