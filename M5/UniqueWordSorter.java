/*
Clint Scott
CSD 402
M5 UniqueWordSorter – Reads words from a file and displays unique sorted results
09/07/2025

This program:
- Reads words from collection_of_words.txt (in the same directory)
- Removes duplicates (case-insensitive)
- Sorts and displays unique words in ascending and descending order
- Shows total words (with and without duplicates)
- Limits display width to readable lines
- Includes simple inline test code (hardcoded list) to validate functionality
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class UniqueWordSorter {
    // Maximum characters per line for formatted output
    private static final int MAX_LINE_WIDTH = 80;

    public static void main(String[] args) {
        // Reference file directly from working directory
        File file = new File("collection_of_words.txt");
        if (!file.exists()) {
            System.err.println("Error: collection_of_words.txt not found.");
            return;
        }

        // Track total and unique words using case-insensitive ordering
        int totalWordCount = 0;
        Set<String> uniqueWords = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // Read file safely with try-with-resources
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                String word = cleanWord(fileScanner.next());
                totalWordCount++;
                if (!word.isEmpty()) {
                    uniqueWords.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        if (uniqueWords.isEmpty()) {
            System.out.println("No valid words found in file.");
            return;
        }

        // Display counts
        System.out.println("Total words including duplicates: " + totalWordCount);
        System.out.println("Total unique words: " + uniqueWords.size() + "\n");

        // Display unique words in ascending order
        System.out.println("--- Unique Words in Ascending Order ---");
        printWordsWithLineLimit(uniqueWords);

        // Display unique words in descending order
        System.out.println("\n--- Unique Words in Descending Order ---");
        List<String> sortedList = new ArrayList<>(uniqueWords);
        Collections.reverse(sortedList);
        printWordsWithLineLimit(sortedList);

        // Run inline test to verify functionality without file input
        runTest();
    }

    /**
     * Normalizes a word by stripping unwanted characters.
     * Allows letters, numbers, dashes, plus signs, and apostrophes.
     * @param input The raw word
     * @return The cleaned word
     */
    private static String cleanWord(String input) {
        return input.replaceAll("[^a-zA-Z0-9\\-\\+']", "");
    }

    /**
     * Prints words with a line length limit for readability.
     * Wraps to the next line if adding another word would exceed the limit.
     * @param words The collection of words to print
     */
    private static void printWordsWithLineLimit(Iterable<String> words) {
        int currentLineLength = 0;
        for (String word : words) {
            // Wrap line if the next word would exceed max width
            if (currentLineLength + word.length() + 1 > MAX_LINE_WIDTH && currentLineLength > 0) {
                System.out.println();
                currentLineLength = 0;
            }
            System.out.print(word + " ");
            currentLineLength += word.length() + 1;
        }
        System.out.println(); // Ensure a newline at the end
    }

    /**
     * Inline test code to validate cleanup, uniqueness, and sorting logic.
     * Uses a hardcoded list with duplicates and special cases (numbers, C++, apostrophe).
     */
    private static void runTest() {
        List<String> testWords = List.of("Dog", "cat", "dog", "apple", "2025", "C++", "Bill's");
        Set<String> testSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (String w : testWords) {
            w = cleanWord(w);
            if (!w.isEmpty()) {
                testSet.add(w);
            }
        }

        System.out.println("\n--- Inline Test Data ---");
        System.out.println("Input: " + testWords);
        System.out.println("Unique: " + testSet);
    }
}