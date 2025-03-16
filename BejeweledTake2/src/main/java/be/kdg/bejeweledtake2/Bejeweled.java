package be.kdg.bejeweledtake2;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.model.Game;
import be.kdg.bejeweledtake2.view.start.StartPresenter;
import be.kdg.bejeweledtake2.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Bejeweled extends Application {
    public Stage mijnStage = new Stage();
    @Override
    public void start(Stage mijnStage) throws IOException{
        this.mijnStage = mijnStage;
        BejeweledModel model = new BejeweledModel();
        StartView view = new StartView();
        mijnStage.setScene(new Scene(view));
        StartPresenter presenter = new StartPresenter(model, view);
        presenter.addWindowEventHandlers();
        mijnStage.setMaximized(true);
        mijnStage.setResizable(false);
        mijnStage.show();
        mijnStage.setMinHeight(mijnStage.getHeight());
        mijnStage.setMinWidth(mijnStage.getWidth());
    }

    public static void main(String[] args) {
        launch();
    }
}