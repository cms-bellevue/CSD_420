/*
Clint Scott
CSD 402
M8 ClintThreeThreadsTest – Unit tests for the ClintThreeThreads application
09/21/2025

This file contains JUnit 5 tests to verify the functionality of the
ClintThreeThreads application. The tests focus on the character
generation methods to ensure they:
1.  Produce the correct number of characters.
2.  Generate characters that match the expected character sets (letters, digits, or specials).
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClintThreeThreadsTest {
    private final ClintThreeThreads app = new ClintThreeThreads();

    /**
     * Tests the generateLetters method to ensure it produces the correct
     * number of characters and that all characters are lowercase letters.
     */
    @Test
    void testGenerateLetters() {
        int count = 10000;
        String result = app.generateLetters(count);
        assertEquals(count, result.length(), "Length should match requested count");
        assertTrue(result.matches("[a-z]+"), "Should contain only lowercase letters");
    }

    /**
     * Tests the generateDigits method to ensure it produces the correct
     * number of characters and that all characters are digits.
     */
    @Test
    void testGenerateDigits() {
        int count = 10000;
        String result = app.generateDigits(count);
        assertEquals(count, result.length(), "Length should match requested count");
        assertTrue(result.matches("[0-9]+"), "Should contain only digits");
    }

    /**
     * Tests the generateSpecials method to ensure it produces the correct
     * number of characters and that all characters are from the specified set.
     */
    @Test
    void testGenerateSpecials() {
        int count = 10000;
        String result = app.generateSpecials(count);
        assertEquals(count, result.length(), "Length should match requested count");
        assertTrue(result.matches("[!@#$%&*]+"), "Should contain only specified special characters");
    }
}