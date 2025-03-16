package be.kdg.bejeweledtake2.view.pregame;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.game.GamePresenter;
import be.kdg.bejeweledtake2.view.game.GameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PreGamePresenter {
    private BejeweledModel model;
    private PreGameView view;
    public PreGamePresenter(BejeweledModel model, PreGameView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {

    }

    private void addEventHandlers() {
        this.view.getTxtNameField().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String currentText = view.txtNameField.getText();
                if(currentText.isEmpty() || !currentText.matches("[a-zA-Z0-9]+")){
                    view.getBtnContinue().setDisable(true);
                }else{
                    view.getBtnContinue().setDisable(false);
                }
            }
        });

        this.view.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.newGame();

                GameView gameView = new GameView();
                new GamePresenter(model, gameView);
                view.getScene().setRoot(gameView);
                Stage stage = (Stage) gameView.getScene().getWindow();
                stage.setMaximized(true);

                model.getCurrentGame().getScore().setPlayerName(view.txtNameField.getText());

                model.getCurrentGame().startTimer();
            }
        });
    }

    public void addWindowEventHandlers(){

    }
}
