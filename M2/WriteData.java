/*
Clint Scott
CSD420 Advanced Java Programming
M2 Programming Assignment – WriteData
08/24/2025

This program:
- Creates arrays of 5 random integers and 5 random doubles
- Writes a timestamp and the array data to a file named ClintScott_datafile.dat
- If the file does not exist, it is created
- If the file already exists, new data is appended
- Uses a constant for array size
- Catches and reports file writing errors gracefully
*/

import java.io.*;
import java.time.LocalDateTime;
import java.util.Random;

public class WriteData {
    private static final int ARRAY_SIZE = 5;

    public static void main(String[] args) {
        String filename = "ClintScott_datafile.dat";
        Random rand = new Random();

        int[] intArray = new int[ARRAY_SIZE];
        double[] doubleArray = new double[ARRAY_SIZE];

        // Generate random integers and doubles
        for (int i = 0; i < ARRAY_SIZE; i++) {
            intArray[i] = rand.nextInt(100);
            doubleArray[i] = rand.nextDouble() * 100;
        }

        // Try-with-resources ensures writers close automatically
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            // Write timestamp
            out.println("Timestamp: " + LocalDateTime.now());

            // Write integer array
            out.print("Integers: ");
            for (int val : intArray) {
                out.print(val + " ");
            }
            out.println();

            // Write double array
            out.print("Doubles: ");
            for (double val : doubleArray) {
                out.printf("%.2f ", val);
            }
            out.println();
            out.println("----------------------------------------");

            System.out.println("Data successfully written to " + filename);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}