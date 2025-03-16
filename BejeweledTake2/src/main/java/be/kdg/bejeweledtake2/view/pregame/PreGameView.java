package be.kdg.bejeweledtake2.view.pregame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.util.Objects;

public class PreGameView extends BorderPane {

    Button btnContinue;
    TextField txtNameField;

    public Button getBtnContinue() {return btnContinue;}

    public TextField getTxtNameField() {return txtNameField;}

    public PreGameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnContinue = new Button();
        this.txtNameField = new TextField();
    }

    private void layoutNodes() {
        //getting screensize so you can resize everything according to the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //resize constants
        final double WIDTH_RATIO = (screenSize.getWidth() / 2039);
        final double HEIGHT_RATIO = (screenSize.getHeight() / 1755);
        final double SCREEN_RATIO = Math.min(WIDTH_RATIO, HEIGHT_RATIO)-0.1;

        //images
        Image imgBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/scoresAssets/Background.png")), 2039 * SCREEN_RATIO/1.3, 1755*SCREEN_RATIO/1.5, false, true);

        //main menu button
        FlowPane button = new FlowPane();
        btnContinue.setText("Continue");
        btnContinue.setMinSize(100*WIDTH_RATIO, 100*HEIGHT_RATIO);
        btnContinue.setAlignment(Pos.CENTER);
        button.getChildren().add(btnContinue);
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(-100*SCREEN_RATIO,0,100*SCREEN_RATIO,0));

        Label lblGameOver = new Label("Enter your name:");
        lblGameOver.setTextAlignment(TextAlignment.CENTER);
        lblGameOver.setAlignment(Pos.CENTER);
        lblGameOver.setTextFill(Color.WHITE);
        lblGameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70*SCREEN_RATIO));

        txtNameField.setPromptText("Your Name");
        txtNameField.setMinSize(50*WIDTH_RATIO,50*HEIGHT_RATIO);

        HBox mijnHBox = new HBox();
        mijnHBox.setAlignment(Pos.CENTER);
        mijnHBox.getChildren().addAll(lblGameOver, txtNameField);
        mijnHBox.setSpacing(50);
        mijnHBox.setPadding(new Insets(100*SCREEN_RATIO,0,-100*SCREEN_RATIO,0));

        this.setBackground(new Background(new BackgroundImage(imgBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.setCenter(mijnHBox);
        mijnHBox.setPadding(new Insets(150*SCREEN_RATIO,0,0,0));
        this.setBottom(button);

    }
}