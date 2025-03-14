package be.kdg.bejeweledtake2.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartView extends BorderPane {

    Button btnPlay;

    public Button getBtnPlay() {return btnPlay;}

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnPlay = new Button();
    }

    private void layoutNodes() {
        //getting screensize so you can resize everything according to the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //resize constants
        final double WIDTH_RATIO = (screenSize.getWidth() / 320);
        final double HEIGHT_RATIO = (screenSize.getHeight() / 240);
        final double SCREEN_RATIO = Math.min(WIDTH_RATIO, HEIGHT_RATIO)-0.1;

        Image startScreen = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/startAssets/BejeweledStartScreen.png")));

        // Figured these out based on a maximized window, won't be correct position on a different size
        btnPlay.setPrefHeight(60);
        btnPlay.setPrefWidth(475);
        btnPlay.setText("Play");
        btnPlay.setOpacity(0);

        // Sets background
        BackgroundSize specialSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false);
        BackgroundImage screenPic = new BackgroundImage(startScreen, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,specialSize);
        this.setBackground(new Background(screenPic));

        VBox mijnVBox = new VBox();
        mijnVBox.getChildren().add(btnPlay);
        this.setBottom(mijnVBox);
        mijnVBox.setAlignment(Pos.CENTER);
        mijnVBox.setPadding(new Insets(30*HEIGHT_RATIO));

        JFrame mijnJFrame = new JFrame();
    }
}
