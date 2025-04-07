package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {

    private static final Set<String> COMMON_WORDS = Set.of(
        "a", "and", "the", "to", "for", "they", "in"
    );

    public static Map<String, List<Integer>> createIndex(String content) {
        Map<String, List<Integer>> index = new HashMap<>();
        String[] words = content.replaceAll("[.,]", "").toLowerCase().split("\\s+");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (COMMON_WORDS.contains(word)) continue;

            index.computeIfAbsent(word, k -> new ArrayList<>()).add(i);
        }

        return index;
    }

    public static void saveIndexToFile(Map<String, List<Integer>> index, String outputPath) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath));
        writer.write("{\n");

        List<String> sortedKeys = new ArrayList<>(index.keySet());
        Collections.sort(sortedKeys);

        for (int i = 0; i < sortedKeys.size(); i++) {
            String word = sortedKeys.get(i);
            writer.write(String.format("  \"%s\": %s", word, index.get(word).toString()));
            if (i != sortedKeys.size() - 1) writer.write(",");
            writer.write("\n");
        }

        writer.write("}\n");
        writer.close();
    }

    public static String readFile(String inputPath) throws IOException {
        return Files.readString(Paths.get(inputPath));
    }
}