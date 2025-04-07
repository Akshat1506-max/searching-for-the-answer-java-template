package org.example;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            String inputPath = "input.txt";
            String outputPath = "inverted_index.txt";

            String content = InvertedIndex.readFile(inputPath);
            Map<String, java.util.List<Integer>> index = InvertedIndex.createIndex(content);
            InvertedIndex.saveIndexToFile(index, outputPath);

            System.out.println("Inverted index created and saved to: " + outputPath);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}