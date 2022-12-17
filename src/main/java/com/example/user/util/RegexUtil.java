package com.example.user.util;

import java.util.regex.Pattern;

public class RegexUtil {
    public static boolean match(String text, String pattern) {
        return Pattern.compile(pattern)
                .matcher(text)
                .matches();
    }
}
