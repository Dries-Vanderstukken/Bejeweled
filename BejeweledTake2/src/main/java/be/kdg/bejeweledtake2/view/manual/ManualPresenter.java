package be.kdg.bejeweledtake2.view.manual;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.credits.CreditsView;
import be.kdg.bejeweledtake2.view.options.OptionsPresenter;
import be.kdg.bejeweledtake2.view.options.OptionsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ManualPresenter {
    private BejeweledModel model;
    private ManualView view;
    public ManualPresenter(BejeweledModel model, ManualView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }
    private void updateView() {
    }
    private void addEventHandlers() {
        this.view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OptionsView optionsView = new OptionsView();
                OptionsPresenter optionsPresenter = new OptionsPresenter(model, optionsView);
                view.getScene().setRoot(optionsView);
            }
        });
    }
    public void addWindowEventHandlers(){

    }
}
