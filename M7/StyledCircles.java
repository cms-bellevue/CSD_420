/*
Clint Scott
CSD 402
StyledCircles – JavaFX Circles with External CSS Styling
09/21/2025

This program:
- Uses JavaFX to display four styled circles
- Loads styles from mystyle.css
- Demonstrates use of CSS class (.plaincircle) and IDs (#redcircle, #greencircle)
- Arranges circles in a horizontal layout to match the sample image
- Adds hover effects to the circles
- Includes a simple test method to verify CSS IDs and class names were applied
*/

// NetBeans package
package csd420_m7_styledcircles;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class StyledCircles extends Application {

    @Override
    public void start(Stage primaryStage) {
        // HBox layout for circles, to match the horizontal layout of the sample image
        HBox hbox = new HBox(20);
        hbox.setPadding(new Insets(20));
        hbox.setAlignment(Pos.CENTER);

        // Create circles using a helper method for cleaner code
        Circle circle1 = createCircleWithClass("plaincircle");
        Circle circle2 = createCircleWithId("redcircle");
        Circle circle3 = createCircleWithId("greencircle");
        Circle circle4 = createCircleWithClass("plaincircle");
        circle4.getStyleClass().add("circleborder"); // Add extra CSS class for circle 4

        // Add circles to HBox
        hbox.getChildren().addAll(circle1, circle2, circle3, circle4);

        // Scene setup with background color
        Scene scene = new Scene(hbox, 500, 300, Color.LIGHTGRAY);

        // Load CSS as a classpath resource
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());

        // Set stage title and show
        primaryStage.setTitle("Styled Circles with CSS");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run basic test to confirm style assignments
        runTests(circle1, circle2, circle3, circle4);
    }

    /**
     * Helper method to create a Circle and add a style class.
     * @param className The name of the CSS class to apply.
     * @return A styled Circle object.
     */
    private Circle createCircleWithClass(String className) {
        Circle circle = new Circle(50);
        circle.getStyleClass().add(className);
        return circle;
    }

    /**
     * Helper method to create a Circle and set a style ID.
     * @param idName The name of the CSS ID to apply.
     * @return A styled Circle object.
     */
    private Circle createCircleWithId(String idName) {
        Circle circle = new Circle(50);
        circle.setId(idName);
        return circle;
    }

    /**
     * Simple test method to confirm style assignments.
     */
    private void runTests(Circle c1, Circle c2, Circle c3, Circle c4) {
        System.out.println("Running style assignment tests...");

        if (!"redcircle".equals(c2.getId())) {
            throw new RuntimeException("Test Failed: Circle 2 should have ID 'redcircle', but has '" + c2.getId() + "'");
        }

        if (!"greencircle".equals(c3.getId())) {
            throw new RuntimeException("Test Failed: Circle 3 should have ID 'greencircle', but has '" + c3.getId() + "'");
        }

        if (!c1.getStyleClass().contains("plaincircle")) {
            throw new RuntimeException("Test Failed: Circle 1 missing 'plaincircle' class");
        }

        if (!c4.getStyleClass().contains("circleborder")) {
            throw new RuntimeException("Test Failed: Circle 4 missing 'circleborder' class");
        }

        System.out.println("All tests passed.");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}