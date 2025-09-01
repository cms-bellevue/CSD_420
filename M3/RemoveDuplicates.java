/*
Clint Scott
CSD 420
M3 Remove Duplicates
08/31/2025

This program:
- Creates an ArrayList containing 50 random integers ranging from 1 to 20.
- Defines a generic method called removeDuplicates() that removes duplicates.
- Returns a new ArrayList with only unique values.
- Displays both the original list and the deduplicated list.
*/

import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicates {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            numbers.add(rand.nextInt(20) + 1);
        }

        System.out.println("Original list (with duplicates):");
        System.out.println(numbers);

        ArrayList<Integer> uniqueNumbers = removeDuplicates(numbers);

        System.out.println("List after removing duplicates:");
        System.out.println(uniqueNumbers);
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();
        for (E element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}