package be.kdg.bejeweledtake2.view.scores;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.model.Score;
import be.kdg.bejeweledtake2.view.start.StartPresenter;
import be.kdg.bejeweledtake2.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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
        List<Score> HighscoreList = null;
        try {
            HighscoreList = model.getCurrentGame().getScore().handleHighscores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i = 0;
            String[] highScoreNames = new String[10];
            int[] highscores = new int[10];
            for (Score score : HighscoreList) {
                highScoreNames[i] = score.getPlayerName();
                highscores[i]  = score.getScore();
                i++;
            }
            view.setHighscores(highscores);
            view.setHighscoreNames(highScoreNames);
        view.getLblScoreLabel().setText("Your Score: "+model.getCurrentGame().getScore().getScore());
    }

    private void addEventHandlers() {
        this.view.getBtnMainMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                new StartPresenter(model, startView);
                view.getScene().setRoot(startView);
                Stage stage = (Stage) startView.getScene().getWindow();
                stage.setMaximized(true);
            }
        });
    }

    public void addWindowEventHandlers(){

    }
}
