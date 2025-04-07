package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testCreateIndex() {
        String text = "The brown cat and the blue rat live in the brown house. They enjoy live music concerts.";
        Map<String, List<Integer>> index = InvertedIndex.createIndex(text);

        assertEquals(List.of(5), index.get("blue"));
        assertEquals(List.of(1, 10), index.get("brown"));
        assertEquals(List.of(7, 14), index.get("live"));
        assertFalse(index.containsKey("the"));
        assertFalse(index.containsKey("and"));
    }
}