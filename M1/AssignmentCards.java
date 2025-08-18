/*
Clint Scott
CSD 420 – Advanced Java Programming
Assignment – Random Card Display
08/17/2025

This JavaFX program:
- Displays four random playing card images selected from a standard deck of 52 cards.
- Users can refresh the display to generate a new set of randomly chosen cards.
- A CSS stylesheet enhances the appearance, giving it a more professional and polished look.

Troubleshooting notes:
- The program expects a subdirectory named cards that contains 52 images labeled from 1.png to 52.png.
- A subdirectory named css should include a file called style.css for visual styling purposes.
- If the images or styles are not loading correctly, ensure the directory structure is set up correctly.
*/

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class AssignmentCards extends Application {

    private static final int DECK_SIZE = 52;
    private static final int DISPLAY_CARDS = 4;
    private HBox cardBox;

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("A Random Hand of Cards");
        titleLabel.getStyleClass().add("title-label");

        cardBox = new HBox(20);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.getStyleClass().add("card-box");

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> displayCards()); // Lambda expression
        refreshButton.getStyleClass().add("refresh-button");

        VBox root = new VBox(20, titleLabel, cardBox, refreshButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root-pane");

        displayCards();

        Scene scene = new Scene(root, 650, 450);

        try {
            // Load CSS directly from the file system
            scene.getStylesheets().add("css/style.css");
        } catch (Exception ex) {
            System.out.println("Warning: CSS not applied. Ensure css/style.css exists in the css folder.");
        }

        primaryStage.setTitle("Random Card Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to randomly display 4 cards
    private void displayCards() {
        cardBox.getChildren().clear();

        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= DECK_SIZE; i++) {
            deck.add(i);
        }
        Collections.shuffle(deck);

        for (int i = 0; i < DISPLAY_CARDS; i++) {
            String filename = "cards/" + deck.get(i) + ".png";
            try {
                // Load images directly from the cards folder
                Image cardImage = new Image(filename);
                ImageView cardView = new ImageView(cardImage);
                cardView.setFitHeight(150);
                cardView.setPreserveRatio(true);

                // Fade-in effect
                FadeTransition ft = new FadeTransition(Duration.millis(750), cardView);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();

                cardBox.getChildren().add(cardView);
            } catch (Exception ex) {
                System.out.println("Error loading image: " + filename);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}