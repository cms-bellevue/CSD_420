/*
Clint Scott
CSD 402
M6 BubbleSortTest – Demonstrates bubble sort using Comparable and Comparator
09/13/2025

This program tests the BubbleSort class with both Comparable and Comparator methods.
It sorts:
- An array of Integers (natural and reverse order)
- An array of Strings themed around the "Kill Tony" podcast (by natural order and by length using Comparator)
*/

import java.util.Arrays;
import java.util.Comparator;

public class BubbleSortTest {

    public static void main(String[] args) {
        // Test Comparable bubbleSort with Integers
        Integer[] numbers = {5, 3, 8, 4, 2};
        System.out.println("Original numbers: " + Arrays.toString(numbers));
        BubbleSort.bubbleSort(numbers);
        System.out.println("Sorted numbers (Comparable): " + Arrays.toString(numbers));

        // Test Comparable bubbleSort with Kill Tony-themed Strings
        String[] words = {"Tony", "Redban", "William", "Hans"};
        System.out.println("\nOriginal words: " + Arrays.toString(words));
        BubbleSort.bubbleSort(words);
        System.out.println("Sorted words (Comparable, alphabetical): " + Arrays.toString(words));

        // Test Comparator bubbleSort with Integers in reverse order
        Integer[] reverseSortedNumbers = {5, 3, 8, 4, 2};
        System.out.println("\nOriginal reverseSortedNumbers: " + Arrays.toString(reverseSortedNumbers));
        BubbleSort.bubbleSort(reverseSortedNumbers, Comparator.reverseOrder());
        System.out.println("Sorted reverseSortedNumbers (Comparator reverse order): " + Arrays.toString(reverseSortedNumbers));

        // Test Comparator bubbleSort with Kill Tony-themed Strings by length
        String[] lengthSortedWords = {"roast", "joke", "mic", "comedy"};
        System.out.println("\nOriginal lengthSortedWords: " + Arrays.toString(lengthSortedWords));
        BubbleSort.bubbleSort(lengthSortedWords, Comparator.comparingInt(String::length));
        System.out.println("Sorted lengthSortedWords (Comparator by length): " + Arrays.toString(lengthSortedWords));
    }

}