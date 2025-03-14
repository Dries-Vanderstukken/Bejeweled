package be.kdg.bejeweledtake2.view.manual;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class ManualView extends BorderPane {

    Button btnExit;

    public Button getBtnExit() {return btnExit;}

    public ManualView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnExit = new Button();
    }

    private void layoutNodes() {
        String manualtext = new String("You start every game with a set amount of time.\n" +
                "By clicking 2 gems, they will swap places.\n" +
                "If you are able to make a row of 3 (or more) gems of the same colour, you will gain points and regain some time.\n" +
                "Special gems can be activated by swapping them into a match.\n" +
                "By creating a row of 4 gems of the same colour, a bomb gem is created which will explode in a 3x3 area once activated.\n" +
                "By creating a row of 5 gems of the same colour, a hypercube is created which will take out every similar gem on the board once activated.\n" +
                "If any matches are made after your swap but before your next swap, these will count towards your bonus points and time.\n" +
                "If your timer runs out, the game ends and your high score will be saved after entering your name.");
        Label label = new Label(manualtext);
        label.setWrapText(true); // Enable text wrapping
        label.setAlignment(Pos.CENTER); // Properly centers the label text inside the label
        label.setPrefSize(500,300); // Set preferred size for the TextArea (width, height)
        label.setMaxSize(500,300);

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
