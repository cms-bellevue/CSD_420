/*
Clint Scott
CSD 402
M6 BubbleSort – Implements bubble sort with Comparable and Comparator
09/14/2025

This program defines two generic bubbleSort methods:
1. bubbleSort(E[] list) – sorts using Comparable
2. bubbleSort(E[] list, Comparator<? super E> comparator) – sorts using Comparator

Test code is provided in BubbleSortTest.java to demonstrate sorting with Strings and Integers.

To run the program, execute BubbleSortTest.java.
*/

import java.util.Comparator;

public class BubbleSort {

    // Bubble sort using Comparable
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;
        for (int pass = 0; pass < list.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < list.length - pass - 1; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // optimization: stop if no swaps
        }
    }

    // Bubble sort using Comparator
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean swapped;
        for (int pass = 0; pass < list.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < list.length - pass - 1; i++) {
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        System.out.println("This class contains sorting methods but no test code.");
        System.out.println("To run the program, execute BubbleSortTest.java instead:");
        System.out.println("  javac BubbleSortTest.java");
        System.out.println("  java BubbleSortTest");
    }

}