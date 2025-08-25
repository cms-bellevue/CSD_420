/*
Clint Scott
CSD420 Advanced Java Programming
M2 Programming Assignment – ReadData
08/24/2025

This program:
- Reads data from ClintScott_datafile.dat
- Displays the contents to the console
- Checks if the file is empty and reports accordingly
- Reports successful file read
- Catches and reports file not found and IO errors
*/

import java.io.*;

public class ReadData {
    public static void main(String[] args) {
        String filename = "ClintScott_datafile.dat";

        // Try-with-resources ensures the file is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean hasData = false;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                hasData = true;
            }
            if (!hasData) {
                System.out.println("File is empty: " + filename);
            } else {
                System.out.println("File read successfully: " + filename);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}