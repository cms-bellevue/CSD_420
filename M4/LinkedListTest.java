/*
Clint Scott
CSD 420
M4 LinkedList Traversal Performance
08/31/2025
 
This program showcases the significant performance difference between two
methods for traversing a Java LinkedList: an Iterator (used implicitly by
the for-each loop) and the inefficient get(index) method. It evaluates this
performance gap with a medium-sized list (50,000 integers) and a very large
list (500,000 integers).
 
The results highlight a fundamental principle regarding the performance of
data structures:

- Traversal using an Iterator is exceptionally fast. An Iterator maintains its
current position in the list, allowing it to move from one element to the
next in constant time, or O(1). This makes the overall traversal time
linear with respect to the number of elements, an O(n) operation.

- In contrast, traversal using get(index) is significantly slower. For a
LinkedList, the get(index) method does not have direct access to elements.
Instead, for each call, it must re-traverse the list from the beginning
(or the end, whichever is closer) to find the element at the specified
index. This means the get(i) operation has a linear time complexity of O(i).
When placed in a loop, the total complexity becomes the sum of each operation's
cost, resulting in a quadratic time complexity of O(n²). As demonstrated by
the test with 500,000 integers, this performance degradation is severe.
 
The conclusion is clear: always use a for-each loop or an Iterator when
iterating over a LinkedList to prevent significant performance penalties.
*/
 
import java.util.LinkedList;
import java.text.NumberFormat;
import java.util.Locale;

public class LinkedListPerformanceTest {

    public static void main(String[] args) {
        // First, run a quick test to validate the program's core functionality.
        System.out.println("--- Validating program functionality ---");
        validateFunctionality();

        // Run the main performance tests with a warm-up phase to allow for JIT optimization.
        System.out.println("\n--- Starting warm-up run with 10,000 integers ---");
        runTraversalTest(10000);
        System.out.println("\n--- Warm-up complete, beginning main tests ---");

        // Test 1: Run the performance test for a list of 50,000 integers.
        System.out.println("\n--- Testing with 50,000 integers ---");
        runTraversalTest(50000);

        System.out.println("\n");

        // Test 2: Rerun the test for a much larger list of 500,000 integers.
        System.out.println("--- Testing with 500,000 integers ---");
        runTraversalTest(500000);
    }

    /**
     * Fills a LinkedList and then measures the time to traverse it using
     * a for-each loop (Iterator) and the get(index) method.
     * @param size The number of integers to store in the list.
     */
    public static void runTraversalTest(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        NumberFormat nf = NumberFormat.getInstance(Locale.US);

        System.out.printf("Populating a list with %s integers...\n", nf.format(size));
        // Fill the list with integers from 0 to size-1.
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Test 1: Traversal using a for-each loop (which uses an Iterator)
        System.out.println("Beginning for-each traversal...");
        long startTime = System.currentTimeMillis();
        for (Integer num : list) {
            // Simply iterate without storing values
        }
        long endTime = System.currentTimeMillis();
        long iteratorTime = endTime - startTime;

        // Test 2: Traversal using get(index)
        System.out.println("Beginning get(index) traversal...");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i); // This triggers a full list walk for each element
        }
        endTime = System.currentTimeMillis();
        long getTime = endTime - startTime;

        System.out.println("\n--- Results ---");
        System.out.printf("Time using for-each (Iterator): %d ms\n", iteratorTime);
        System.out.printf("Time using get(index): %d ms\n", getTime);
        System.out.println("---------------------------------------------------");
    }

    /**
     * A basic test to ensure the program's core functionality is correct.
     * This checks that the list is populated with the correct number of elements
     * and that the traversal methods do not throw exceptions for a small list.
     */
    public static void validateFunctionality() {
        final int testSize = 10;
        LinkedList<Integer> list = new LinkedList<>();
        
        // Populate the list
        for (int i = 0; i < testSize; i++) {
            list.add(i);
        }

        // Test list size
        if (list.size() != testSize) {
            System.err.println("Functional Test Failed: List size is incorrect.");
            return;
        }

        // Test iterator traversal
        try {
            for (Integer num : list) {
                // Do nothing, just iterate
            }
            System.out.println("for-each traversal test passed.");
        } catch (Exception e) {
            System.err.println("Functional Test Failed: for-each loop threw an exception.");
            return;
        }

        // Test get(index) traversal
        try {
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
            System.out.println("get(index) traversal test passed.");
        } catch (Exception e) {
            System.err.println("Functional Test Failed: get(index) loop threw an exception.");
            return;
        }

        System.out.println("All basic functional tests passed successfully.");
    }
}