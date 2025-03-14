package be.kdg.bejeweledtake2.view.scores;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.credits.CreditsView;
import be.kdg.bejeweledtake2.view.start.StartPresenter;
import be.kdg.bejeweledtake2.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ScoresPresenter {
    private BejeweledModel model;
    private ScoresView view;
    public ScoresPresenter(BejeweledModel model, ScoresView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
    }

    private void addEventHandlers() {
        this.view.getBtnMainMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                StartPresenter startPresenter = new StartPresenter(model, startView);

            }
        });
    }

    public void addWindowEventHandlers(){

    }
}
