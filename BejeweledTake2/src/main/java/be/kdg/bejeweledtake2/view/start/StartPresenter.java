package be.kdg.bejeweledtake2.view.start;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.pregame.PreGamePresenter;
import be.kdg.bejeweledtake2.view.pregame.PreGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.awt.*;

public class StartPresenter {
    private BejeweledModel model;
    private StartView view;
    public StartPresenter(BejeweledModel model, StartView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }
    private void updateView() {
    }
    private void addEventHandlers() {
        this.view.getBtnPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("geklikt op play");
                //getting screensize so you can resize everything according to the screen resolution
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                //resize constants
                final double WIDTH_RATIO = (screenSize.getWidth() / 2039);
                final double HEIGHT_RATIO = (screenSize.getHeight() / 1755);
                final double SCREEN_RATIO = Math.min(WIDTH_RATIO, HEIGHT_RATIO)-0.1;

                PreGameView preGameView = new PreGameView();
                new PreGamePresenter(model, preGameView);

                view.getScene().setRoot(preGameView);
                Stage stage = (Stage) preGameView.getScene().getWindow();
                stage.setHeight(1755*(SCREEN_RATIO/1.5)+23);
                stage.setWidth(2039*(SCREEN_RATIO/1.3)+10);
                stage.setMinHeight(1755*(SCREEN_RATIO/1.5)+23);
                stage.setMinWidth(2039*(SCREEN_RATIO/1.3)+10);
                stage.setMaximized(false);
            }
        });
    }
    public void addWindowEventHandlers(){

    }
}
