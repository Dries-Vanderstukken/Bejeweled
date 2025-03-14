package be.kdg.bejeweledtake2.view.options;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.credits.CreditsPresenter;
import be.kdg.bejeweledtake2.view.credits.CreditsView;
import be.kdg.bejeweledtake2.view.manual.ManualPresenter;
import be.kdg.bejeweledtake2.view.manual.ManualView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.awt.*;


public class OptionsPresenter {
    private BejeweledModel model;
    private OptionsView view;

    public OptionsPresenter(BejeweledModel model, OptionsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }
    private void updateView() {

    }
    private void addEventHandlers() {
        this.view.getBtnAboutUs().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreditsView creditsView = new CreditsView();
                CreditsPresenter creditsPresenter = new CreditsPresenter(model, creditsView);
                view.getScene().setRoot(creditsView);
            }
        });

        this.view.getBtnManual().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ManualView manualView = new ManualView();
                ManualPresenter manualPresenter = new ManualPresenter(model, manualView);
                view.getScene().setRoot(manualView);
            }
        });
    }
    public void addWindowEventHandlers(){

    }
}
