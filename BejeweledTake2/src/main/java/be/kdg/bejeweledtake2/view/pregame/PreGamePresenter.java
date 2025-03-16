package be.kdg.bejeweledtake2.view.pregame;

import be.kdg.bejeweledtake2.model.BejeweledModel;
import be.kdg.bejeweledtake2.view.game.GamePresenter;
import be.kdg.bejeweledtake2.view.game.GameView;
import be.kdg.bejeweledtake2.view.start.StartPresenter;
import be.kdg.bejeweledtake2.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;

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
//        this.view.getTxtNameField().setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(javafx.scene.input.KeyEvent keyEvent) {
//                String currentText = view.txtNameField.getText();
//                if(currentText.equals("") || !currentText.matches("[a-b]|[A-B]+")){
//                    view.getBtnVolgende().setDisable(true);
//                    view.getTxtBreedte().setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
//                }else{
//                    view.getBtnVolgende().setDisable(false);
//                    view.getTxtBreedte().setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
//
//                }
//            }
//        });

        this.view.getBtnContinue().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.newGame();

                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(model, gameView);
                view.getScene().setRoot(gameView);
                Stage stage = (Stage) gameView.getScene().getWindow();
                stage.setMaximized(true);

                model.getCurrentGame().getScore().setPlayerName(view.txtNameField.getText());
                System.out.println(view.txtNameField.getText());

                model.getCurrentGame().startTimer();
            }
        });
    }

    public void addWindowEventHandlers(){

    }
}
