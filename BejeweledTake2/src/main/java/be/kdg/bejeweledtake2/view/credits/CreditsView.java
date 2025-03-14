package be.kdg.bejeweledtake2.view.credits;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CreditsView extends BorderPane {

    Button btnExit;

    public Button getBtnExit() {return btnExit;}

    public CreditsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnExit = new Button();
    }

    private void layoutNodes() {
        String creditsText = new String("We are:  Dries (Bronze 2 9 LP, critically acclaimed briar midlane player),\n" +
                "and: Sam (Bronze 4 0 LP, jacking off all trades since '07),\n" +
                "2 college students doing ACS at Karel De Grote Hoge School.\n\n" +
                "This game is our simplified version of Bejeweled™ (by Popcap Games©) made in JavaFX.\n" +
                "We worked together on this project for 6 weeks, upgrading it bit by bit into the version you're playing right now.\n" +
                " \n" +
                "Credits:\n" +
                "General Cohesion \t\t\tDries\n" +
                "Game screen, Highscores \tDries\n" +
                "Settings, Info screens \t\tSam\n" +
                "Game Logic\t\t\t\tSam & Dries\n" +
                "Peer tutors \t\t\t\tRob & Seppe");
        Label label = new Label(creditsText);
        label.setWrapText(true); // Enable text wrapping
        label.setAlignment(Pos.CENTER); // Properly centers the label text inside the label
        label.setPrefSize(500,330); // Set preferred size for the TextArea (width, height)
        label.setMaxSize(500,330);

        btnExit.setText("Exit");
        // Create a VBox to limit the height of the TextArea
        VBox vbox = new VBox(label, btnExit);
        vbox.setStyle("-fx-alignment: center;"); // Center the content of the VBox
        vbox.setPrefHeight(200);// Set a preferred height for the VBox
        vbox.setPadding(new Insets(50,0,50,0));

        // Create an HBox to center the VBox horizontally
        HBox hbox = new HBox(vbox);
        hbox.setStyle("-fx-alignment: center;"); // Center the content of the HBox


        // Make stackpane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(hbox);

        // Create a BorderPane
        this.setCenter(stackPane); // Set the HBox in the center of the BorderPane
        this.setMaxSize(1920, 1010);

        // Load the background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/optionsAssets/Options menu.png"),300*2, 189*2, true, true); // Use the correct path to your image
        BackgroundImage myBackground = new BackgroundImage(
                backgroundImage,
                null, // No specific repeat
                null, // No specific stretch
                null, // No specific position
                new BackgroundSize(100, 100, false, true, true, true) // Stretch to fit
        );
        stackPane.setPrefHeight(1010);
        StackPane.setAlignment(stackPane,Pos.CENTER);
        // Sets background (stolen from the start screen code)
        BackgroundSize specialSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false);
        BackgroundImage screenPic = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,specialSize);
        stackPane.setBackground(new Background(screenPic));

        this.setMaxHeight(1010);
        this.setPrefHeight(backgroundImage.getHeight());
    }
}
