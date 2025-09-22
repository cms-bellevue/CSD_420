/*
Clint Scott
CSD 402
M8 ClintThreeThreads – Demonstrates multithreading with random character output
09/21/2025

This program creates three threads to generate and display random characters.
It uses JavaFX for the user interface, with each thread responsible for a different character set:
1. Random lowercase letters (a-z)
2. Random digits (0-9)
3. Random special characters (!, @, #, $, %, &, *)

The output is displayed in separate TextAreas, and each thread's output is
generated with a minimum of 10,000 characters. The program efficiently
updates the UI from separate worker threads using Platform.runLater().
*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Random;

public class ClintThreeThreads extends Application {

    private static final int MIN_CHARS = 10000;
    private static final int MAX_CHARS = 25000;
    private final Random random = new Random();

    private TextArea letterArea;
    private TextArea digitArea;
    private TextArea specialArea;

    private Label letterLabel;
    private Label digitLabel;
    private Label specialLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize UI components for each thread's output
        letterLabel = createLabel("Random Letters");
        letterArea = createTextArea();

        digitLabel = createLabel("Random Digits");
        digitArea = createTextArea();

        specialLabel = createLabel("Random Specials");
        specialArea = createTextArea();

        // Create a GridPane for a structured layout of labels and text areas
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add labels and text areas to the grid
        gridPane.add(letterLabel, 0, 0);
        gridPane.add(letterArea, 0, 1);
        gridPane.add(digitLabel, 0, 2);
        gridPane.add(digitArea, 0, 3);
        gridPane.add(specialLabel, 0, 4);
        gridPane.add(specialArea, 0, 5);

        // Configure the components to grow with the window, filling available space
        GridPane.setHgrow(letterArea, Priority.ALWAYS);
        GridPane.setVgrow(letterArea, Priority.ALWAYS);

        GridPane.setHgrow(digitArea, Priority.ALWAYS);
        GridPane.setVgrow(digitArea, Priority.ALWAYS);

        GridPane.setHgrow(specialArea, Priority.ALWAYS);
        GridPane.setVgrow(specialArea, Priority.ALWAYS);

        Scene scene = new Scene(gridPane);

        primaryStage.setTitle("ClintThreeThreads Output");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start threads to generate characters with named threads for better debugging
        Thread letterThread = new Thread(() -> generateAndAppend(letterArea, letterLabel, this::generateLetters));
        letterThread.setName("LetterThread");
        letterThread.start();

        Thread digitThread = new Thread(() -> generateAndAppend(digitArea, digitLabel, this::generateDigits));
        digitThread.setName("DigitThread");
        digitThread.start();

        Thread specialThread = new Thread(() -> generateAndAppend(specialArea, specialLabel, this::generateSpecials));
        specialThread.setName("SpecialThread");
        specialThread.start();
    }

    /**
     * Helper method to create and configure a TextArea.
     * @return a configured TextArea
     */
    private TextArea createTextArea() {
        TextArea area = new TextArea();
        area.setEditable(false);
        area.setWrapText(true);
        return area;
    }

    /**
     * Helper method to create a Label with a specific text.
     * @param text The text to display on the label.
     * @return a configured Label
     */
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 14));
        return label;
    }

    /**
     * Handles the character generation on a separate thread and updates the UI on the JavaFX Application Thread.
     *
     * @param area The TextArea to which the output will be appended.
     * @param label The Label to update with the character count.
     * @param generator The functional interface for character generation logic.
     */
    private void generateAndAppend(TextArea area, Label label, Generator generator) {
        // Generate a random count for this specific thread, ensuring it's at least MIN_CHARS
        int charCount = MIN_CHARS + random.nextInt(MAX_CHARS - MIN_CHARS + 1);
        
        String output = generator.generate(charCount);
        Platform.runLater(() -> {
            area.setText(output);
            label.setText(label.getText() + " (Count: " + NumberFormat.getIntegerInstance().format(output.length()) + ")");
        });
    }

    /**
     * Generates a string of random lowercase letters.
     *
     * @param count The number of characters to generate.
     * @return The generated string.
     */
    String generateLetters(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    /**
     * Generates a string of random digits.
     *
     * @param count The number of characters to generate.
     * @return The generated string.
     */
    String generateDigits(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append((char) ('0' + random.nextInt(10)));
        }
        return sb.toString();
    }

    /**
     * Generates a string of random special characters.
     *
     * @param count The number of characters to generate.
     * @return The generated string.
     */
    String generateSpecials(int count) {
        char[] specials = {'!', '@', '#', '$', '%', '&', '*'};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(specials[random.nextInt(specials.length)]);
        }
        return sb.toString();
    }

    /**
     * Functional interface for character generation logic, allowing a common method
     * to call different generation logic.
     */
    @FunctionalInterface
    private interface Generator {
        String generate(int count);
    }
}