package com.demo.util;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileProcessor {

    private Map<String, Integer> wordCount = new HashMap<>();
    private String absoluteFilePath;
    private final String specialCharacterPattern = "[^A-Za-z0-9]";

    public FileProcessor(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    public void printWordCounts() {

        if (!StringUtils.isEmpty(absoluteFilePath)) {
            Path file = new File(absoluteFilePath).toPath();
            if (Files.exists(file)
                    && Files.isRegularFile(file)
                    && !Files.isDirectory(file)) {

            try (Stream<String> stream = Files.lines(Paths.get(absoluteFilePath))) {

                String tokens;
                stream.forEach(line ->
                        populateWordCountMap(line)
                );

            } catch (IOException e) {
                e.printStackTrace();
            }

            wordCount.forEach((key, value) -> {
                System.out.println("Count : " + value + " Word : " + key);
            });

            } else {
                System.err.println("Invalid file path specified");
            }
        } else {
            System.err.println("No file specified");
        }
    }

    private void populateWordCountMap(String line) {
        if (!StringUtils.isEmpty(line)) {
            String[] tokens = line.split("\\s+");
            if (tokens != null && tokens.length > 0) {
                Arrays.stream(tokens).forEach(token ->
                        updateWordCount(token)
                );
            }
        }
    }

    private void updateWordCount(String token) {
        if (wordHasSpecialCharacter(token)) {
            token = processWordWithSpecialCharacter(token);
        }
        updateWordCountMap(token);
    }

    public void updateWordCountMap(String word) {
        if (!StringUtils.isEmpty(word)) {
            if (wordCount.putIfAbsent(word.toLowerCase(), 1) != null) {
                wordCount.put(word.toLowerCase(), wordCount.get(word.toLowerCase()) + 1);
            }
        }
    }

    private String processWordWithSpecialCharacter(String token) {
        if (!StringUtils.isEmpty(token)) {
            if (token.length() > 1) {
                if (token.contains("/")) {
                    String[] tokens = token.split("/");
                    for (String word : tokens) {
                        return processWordWithSpecialCharacter(word);
                    }
                } else if (wordHasSpecialCharacter(token.substring(0, 1))
                        && wordHasSpecialCharacter(token.substring(token.length() - 1, token.length()))) {
                    return processWordWithSpecialCharacter(token.substring(1, token.length() -1));
                } else {
                    if (wordHasSpecialCharacter(token.substring(0, 1))) {
                        return processWordWithSpecialCharacter(token.substring(0, 1));
                    }

                    if (wordHasSpecialCharacter(token.substring(token.length() - 1, token.length()))) {
                        return processWordWithSpecialCharacter(token.substring(0, token.length() - 1));
                    }
                }
            } else {
                token = "";
            }
        }

        return token;
    }

    private boolean wordHasSpecialCharacter(String word) {
        if (!StringUtils.isEmpty(word)) {
            return Pattern.compile(specialCharacterPattern).matcher(word).find();
        }
        return false;
    }

    private boolean d(String word) {
        if (!StringUtils.isEmpty(word)) {
            return Pattern.compile(specialCharacterPattern).matcher(word).find();
        }
        return false;
    }
}