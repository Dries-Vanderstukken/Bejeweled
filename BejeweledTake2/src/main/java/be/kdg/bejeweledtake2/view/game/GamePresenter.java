package be.kdg.bejeweledtake2.view.game;

import be.kdg.bejeweledtake2.model.*;
import be.kdg.bejeweledtake2.view.options.OptionsPresenter;
import be.kdg.bejeweledtake2.view.options.OptionsView;
import be.kdg.bejeweledtake2.view.scores.ScoresPresenter;
import be.kdg.bejeweledtake2.view.scores.ScoresView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class GamePresenter {
    private BejeweledModel model;
    private GameView view;
    public GamePresenter(BejeweledModel model, GameView view) {
        this.model = model;
        this.model.getCurrentGame().setPresenter(this);
        this.view = view;
        addEventHandlers();
        updateView();
    }
    private void updateView() {
        view.getGameGrid().getChildren().clear();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile currentTile = this.model.getCurrentGame().getPlayingField()[x][y];
                Button huidigeButton = new Button();

                huidigeButton.setMinSize(51*view.SCREEN_RATIO, 51*view.SCREEN_RATIO);
                
                int gemIndex = 0;
                switch (currentTile.getGem().getGemColor()){
                    case BLUE:
                        gemIndex = 0;
                        break;
                    case RED:
                        gemIndex = 5;
                        break;
                    case YELLOW:
                        gemIndex =  6;
                        break;
                    case GREEN:
                        gemIndex = 1;
                        break;
                    case PINK:
                        gemIndex = 2;
                        break;
                    case WHITE:
                        gemIndex = 3;
                        break;
                    case ORANGE:
                        gemIndex = 4;
                        break;
                    case NONE:
                        gemIndex = 0;
                        break;
                }

                Image[] normalGems = {view.imgBlueGem, view.imgGreenGem, view.imgPinkGem, view.imgWhiteGem, view.imgOrangeGem, view.imgRedGem, view.imgYellowGem};
                Image[] animatedGems = {view.imgBlueGemAnim, view.imgGreenGemAnim, view.imgPinkGemAnim, view.imgWhiteGemAnim, view.imgOrangeGemAnim, view.imgRedGemAnim, view.imgYellowGemAnim};
                Image[] bombGems = {view.imgBlueGemBomb, view.imgGreenGemBomb, view.imgPinkGemBomb, view.imgWhiteGemBomb, view.imgOrangeGemBomb, view.imgRedGemBomb, view.imgYellowGemBomb};

                if (currentTile.getGem().getGemMutation() == GemMutation.NONE){
                    if (currentTile.getStatus() == TileStatus.SELECTED) {
                        huidigeButton.setBackground(new Background(new BackgroundImage(animatedGems[gemIndex], BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                    } else {
                        huidigeButton.setBackground(new Background(new BackgroundImage(normalGems[gemIndex], BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                    }
                } else {
                    if (currentTile.getGem().getGemMutation() == GemMutation.BOMB){
                        huidigeButton.setBackground(new Background(new BackgroundImage(bombGems[gemIndex], BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                    } else {
                        huidigeButton.setBackground(new Background(new BackgroundImage(view.imgHyperCube, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                    }
                }

                if (currentTile.getStatus() == TileStatus.SELECTED) {
                    huidigeButton.setBorder(new Border(new BorderStroke(Color.AQUA, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(5))));
                }
                else if (currentTile.getStatus() == TileStatus.POSSIBLE_MOVE) {
                    huidigeButton.setBorder(new Border(new BorderStroke(Color.LIGHTGREEN, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(5))));
                } else if (currentTile.getStatus() == TileStatus.EMPTY) {
                    huidigeButton.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(5))));
                }

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
                view.getGameGrid().getChildren();
                view.getGameGrid().add(huidigeButton, x, y);
            }
        }
    }
    private void addEventHandlers() {
        this.view.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("geklikt op new game");
                model.getCurrentGame().gameOver();
                updateView();
            }
        });

        this.view.getBtnOptions().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("geklikt op options");

                OptionsView optionsView = new OptionsView();
                new OptionsPresenter(model, optionsView);
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

    public void updateScore(int score, int totalMoveScore){
        view.lblScore.setText(Integer.toString(score));
        if (totalMoveScore != 0) {
            view.lblScoreBall.setText("+" + Integer.toString(totalMoveScore));
        }
    }

    public void updateTimer(long currentTime){
        long seconds = currentTime % 60;
        long minutes = (currentTime - seconds) / 60;
        view.lblTimer.setText(minutes+":"+seconds);
    }

    public void showHighScores(){
        ScoresView scoresView = new ScoresView();
        new ScoresPresenter(model, scoresView);

        //getting screensize so you can resize everything according to the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //resize constants
        final double WIDTH_RATIO = (screenSize.getWidth() / 2039);
        final double HEIGHT_RATIO = (screenSize.getHeight() / 1755);
        final double SCREEN_RATIO = Math.min(WIDTH_RATIO, HEIGHT_RATIO)-0.1;

        view.getScene().setRoot(scoresView);
        Stage stage = (Stage) scoresView.getScene().getWindow();
        stage.setMinHeight(1755*SCREEN_RATIO+23);
        stage.setMinWidth(2039*SCREEN_RATIO+10);
        stage.setMaximized(false);
    }
}
