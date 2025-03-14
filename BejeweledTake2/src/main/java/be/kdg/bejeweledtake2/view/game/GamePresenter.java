package be.kdg.bejeweledtake2.view.game;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.options.OptionsPresenter;
import be.kdg.bejeweledtake2.view.options.OptionsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
