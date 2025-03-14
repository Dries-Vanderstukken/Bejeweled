package be.kdg.bejeweledtake2.view.game;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.model.Tile;
import be.kdg.bejeweledtake2.model.TileStatus;
import be.kdg.bejeweledtake2.view.options.OptionsPresenter;
import be.kdg.bejeweledtake2.view.options.OptionsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePresenter {
    private BejeweledModel model;
    private GameView view;
    public GamePresenter(BejeweledModel model, GameView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }
    private void updateView() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile currentTile = this.model.getCurrentGame().getPlayingField()[x][y];
                Button huidigeButton = new Button();
                huidigeButton.setPrefWidth(51*view.SCREEN_RATIO);
                huidigeButton.setPrefHeight(51*view.SCREEN_RATIO);

                if (currentTile.getStatus() == TileStatus.SELECTED) {
                    huidigeButton.setBackground(new Background(new BackgroundImage(view.imgBlueGemAnim, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                }
                else {
                    huidigeButton.setBackground(new Background(new BackgroundImage(view.imgBlueGem, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                }

                huidigeButton.setFont(new Font("Arial", 20));
                int mijnX = x;
                int mijnY = y;
                huidigeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        //Alle code die te maken heeft met de aanpassing van het model moet eigenlijk in de modelklasses staan.
                        //We laten
                        System.out.println("U heeft geklikt op de coordinaat: (" + mijnX + "," + mijnY + ")");
                        model.getCurrentGame().tileClicked(mijnX, mijnY);
                        updateView();
                    }
                });
                view.getGameGrid().add(huidigeButton, x, y);
            }
        }
    }
    private void addEventHandlers() {
        this.view.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("geklikt op new game");
            }
        });

        this.view.getBtnOptions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("geklikt op options");

                OptionsView optionsView = new OptionsView();
                OptionsPresenter optionsPresenter = new OptionsPresenter(model, optionsView);
                Stage optionsStage = new Stage();
                optionsStage.initModality(Modality.APPLICATION_MODAL);
                optionsStage.setScene(new Scene(optionsView));
                optionsStage.setResizable(false);
                optionsStage.showAndWait();
            }
        });

        this.view.getBtnQuitGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("geklikt op quit");
                System.exit(0);
            }
        });

    }
    public void addWindowEventHandlers(){

    }
}
