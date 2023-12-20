package com.example.demo.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtractor {

    public static String extractURLFromJSON(String message) {


        // Proba bezposredniego wyciagniecia URL z pola "message"
        Pattern pattern = Pattern.compile("(https?://\\S+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
