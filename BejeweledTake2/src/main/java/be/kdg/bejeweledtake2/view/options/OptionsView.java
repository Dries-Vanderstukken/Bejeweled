package be.kdg.bejeweledtake2.view.options;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class OptionsView extends BorderPane {

    Slider sldrVolume;
    Slider sldrMusic;
    Slider sldrSFX;
    Slider sldrVoices;
    Button btnManual;
    Button btnAboutUs;

    public Slider getSldrVolume() {return sldrVolume;}
    public Slider getSldrMusic() {return sldrMusic;}
    public Slider getSldrSFX() {return sldrSFX;}
    public Slider getSldrVoices() {return sldrVoices;}
    public Button getBtnManual() {return btnManual;}
    public Button getBtnAboutUs() {return btnAboutUs;}

    public OptionsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.sldrVolume = new Slider();
        this.sldrMusic = new Slider();
        this.sldrSFX = new Slider();
        this.sldrVoices = new Slider();
        this.btnManual = new Button();
        this.btnAboutUs = new Button();
    }

    private void layoutNodes() {
        Label lblVolume = new Label("All volume:");
        lblVolume.setTextAlignment(TextAlignment.CENTER);
        lblVolume.setAlignment(Pos.CENTER);
        lblVolume.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));

        Label lblMusic = new Label("Music:");
        lblMusic.setTextAlignment(TextAlignment.CENTER);
        lblMusic.setAlignment(Pos.CENTER);
        lblMusic.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));

        Label lblSFX = new Label("Sounds:");
        lblSFX.setTextAlignment(TextAlignment.CENTER);
        lblSFX.setAlignment(Pos.CENTER);
        lblSFX.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));

        Label lblVoices = new Label("Voice:");
        lblVoices.setTextAlignment(TextAlignment.CENTER);
        lblVoices.setAlignment(Pos.CENTER);
        lblVoices.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));

        btnManual.setText("Game guide");
        btnAboutUs.setText("About us");

        // Needed for centering the about us button in its own cell
        StackPane aboutUsCenterer = new StackPane();
        aboutUsCenterer.getChildren().add(btnAboutUs);
        GridPane.setHalignment(btnAboutUs, HPos.CENTER);

        // Needed for centering the about us button in its own cell
        StackPane manualCenterer = new StackPane();
        manualCenterer.getChildren().add(btnManual);
        GridPane.setHalignment(btnManual, HPos.CENTER);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/optionsAssets/Options menu.png")),300*2, 189*2, true, true);
        // Makes it fill the entire area:
        BackgroundSize newSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false);
        BackgroundImage backgroundImageImage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,newSize);
        Background background = new Background(backgroundImageImage);

        // Makes the sliders already have set values (looks more real that way)
        sldrVolume.setValue(50);
        sldrVolume.setScaleX(1.5);
        sldrVolume.setScaleY(1.5);

        sldrMusic.setValue(100);
        sldrMusic.setScaleX(1.5);
        sldrMusic.setScaleY(1.5);

        sldrSFX.setValue(100);
        sldrSFX.setScaleX(1.5);
        sldrSFX.setScaleY(1.5);

        sldrVoices.setValue(100);
        sldrVoices.setScaleX(1.5);
        sldrVoices.setScaleY(1.5);

        // Creates gridpane and adds everything to it accordingly
        GridPane myGridPane = new GridPane();
        myGridPane.add(lblVolume,0,0);
        myGridPane.add(sldrVolume,1,0);
        myGridPane.add(lblMusic,0,1);
        myGridPane.add(sldrMusic,1,1);
        myGridPane.add(lblSFX,0,2);
        myGridPane.add(sldrSFX,1,2);
        myGridPane.add(lblVoices,0,3);
        myGridPane.add(sldrVoices,1,3);
        myGridPane.add(btnManual,0,4);
        myGridPane.add(aboutUsCenterer,1,4);

        myGridPane.setHgap(40);
        myGridPane.setVgap(10);
        myGridPane.setAlignment(Pos.CENTER);
        myGridPane.setBackground(background);
        myGridPane.setMinSize(backgroundImage.getWidth(), backgroundImage.getHeight());
        myGridPane.setMaxSize(1920,1080);

        this.setCenter(myGridPane);
    }
}
