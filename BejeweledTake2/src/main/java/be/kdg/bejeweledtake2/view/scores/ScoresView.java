package be.kdg.bejeweledtake2.view.scores;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Objects;

public class ScoresView extends BorderPane {

    Button btnMainMenu;
    Label lblScoreLabel;
    TextField txtNameField;
    String[] highscoreNames;
    int[] highscores;

    public int[] getHighscores() {return highscores;}
    public String[] getHighscoreNames() {return highscoreNames;}
    public TextField getTxtNameField() {return txtNameField;}
    public Label getLblScoreLabel() {return lblScoreLabel;}
    public Button getBtnMainMenu() {return btnMainMenu;}

    public ScoresView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.btnMainMenu = new Button();
        this.lblScoreLabel = new Label();
        this.txtNameField = new TextField();
        this.highscoreNames = new String[10];
        this.highscores = new int[10];
    }

    private void layoutNodes() {
        //getting screensize so you can resize everything according to the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //resize constants
        final double WIDTH_RATIO = (screenSize.getWidth() / 2039);
        final double HEIGHT_RATIO = (screenSize.getHeight() / 1755);
        final double SCREEN_RATIO = Math.min(WIDTH_RATIO, HEIGHT_RATIO)-0.1;

        //images
        Image imgBackground = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/scoresAssets/Background.png")), 2039 * SCREEN_RATIO, 1755*SCREEN_RATIO, true, true);
        Image imgHighscores = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/scoresAssets/highscores.png")), 1582 * SCREEN_RATIO*0.9, 1294 * SCREEN_RATIO*0.9, true, true);
        Image imgMainMenuButton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/scoresAssets/main menu button.png")), 520 * SCREEN_RATIO, 119 * SCREEN_RATIO, true, true);

        //main menu button
        FlowPane button = new FlowPane();
        btnMainMenu.setBackground(new Background(new BackgroundImage(imgMainMenuButton, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        btnMainMenu.setMinSize(imgMainMenuButton.getWidth(), imgMainMenuButton.getHeight());
        btnMainMenu.setAlignment(Pos.CENTER);
        button.getChildren().add(btnMainMenu);
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(-100*SCREEN_RATIO,0,100*SCREEN_RATIO,0));

        Label lblGameOver = new Label("Game Over!");
        lblGameOver.setTextAlignment(TextAlignment.CENTER);
        lblGameOver.setAlignment(Pos.CENTER);
        lblGameOver.setTextFill(Color.WHITE);
        lblGameOver.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70*SCREEN_RATIO));

        lblScoreLabel.setText("Your Score: 0");
        lblScoreLabel.setTextAlignment(TextAlignment.CENTER);
        lblScoreLabel.setAlignment(Pos.CENTER);
        lblScoreLabel.setTextFill(Color.WHITE);
        lblScoreLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70*SCREEN_RATIO));

        txtNameField.setPromptText("Your Name");

        //High scores
        VBox mijnVBox = new VBox();
        for (int i=0; i<10; i++){
            FlowPane mijnFlowPane = new FlowPane();

            Label lbl = new Label();
            lbl.setMaxWidth(600*SCREEN_RATIO);
            lbl.setPrefWidth(600*SCREEN_RATIO);
            lbl.setText((i+1)+". "+highscoreNames[i]);
            lbl.setTextAlignment(TextAlignment.LEFT);
            lbl.setAlignment(Pos.CENTER_LEFT);
            lbl.setTextFill(Color.WHITE);
            lbl.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70*SCREEN_RATIO));


            Label lbl2 = new Label(Integer.toString(highscores[i]));
            lbl2.setMaxWidth(600*SCREEN_RATIO);
            lbl2.setTextAlignment(TextAlignment.RIGHT);
            lbl2.setAlignment(Pos.CENTER_RIGHT);
            lbl2.setTextFill(Color.WHITE);
            lbl2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 70*SCREEN_RATIO));

            mijnFlowPane.setHgap(50*SCREEN_RATIO);
            //
            mijnFlowPane.getChildren().addAll(lbl,lbl2);
            mijnFlowPane.setMaxWidth(1850*SCREEN_RATIO);
            mijnFlowPane.setAlignment(Pos.CENTER_LEFT);

            HBox mijnHBox = new HBox();
            mijnHBox.setAlignment(Pos.CENTER);
            mijnHBox.getChildren().add(mijnFlowPane);
            mijnVBox.getChildren().add(mijnHBox);
        }

        mijnVBox.setBackground(new Background(new BackgroundImage(imgHighscores, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        mijnVBox.setAlignment(Pos.CENTER);

        HBox mijnHBox = new HBox();
        mijnHBox.setAlignment(Pos.CENTER);
        mijnHBox.getChildren().addAll(lblScoreLabel, txtNameField);
        mijnHBox.setSpacing(50);

        VBox topVBox = new VBox();
        topVBox.setAlignment(Pos.CENTER);
        topVBox.getChildren().addAll(lblGameOver, mijnHBox);
        topVBox.setSpacing(10);
        topVBox.setPadding(new Insets(100*SCREEN_RATIO,0,-100*SCREEN_RATIO,0));

        this.setBackground(new Background(new BackgroundImage(imgBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        this.setCenter(mijnVBox);
        mijnVBox.setPadding(new Insets(150*SCREEN_RATIO,0,0,0));
        this.setBottom(button);
        this.setTop(topVBox);
    }
}