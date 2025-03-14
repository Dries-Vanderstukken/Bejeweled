package be.kdg.bejeweledtake2.view.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GameView extends BorderPane {
    //getting screensize so you can resize everything according to the screen resolution
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //resize constants
    final double WIDTH_RATIO = (screenSize.getWidth() / 640);
    final double HEIGHT_RATIO = (screenSize.getHeight() / 480);
    final double SCREEN_RATIO = Math.min(WIDTH_RATIO, HEIGHT_RATIO)-0.1;

    Label lblScore;
    Button btnNewGame;
    Button btnOptions;
    Label lblScoreBall;
    Button btnQuitGame;
    Label lblTimer;
    Image imgBlueGem;
    Image imgGreenGem;
    Image imgOrangeGem;
    Image imgPinkGem;
    Image imgRedGem;
    Image imgWhiteGem;
    Image imgYellowGem;
    Image imgBlueGemAnim;
    Image imgGreenGemAnim;
    Image imgOrangeGemAnim;
    Image imgPinkGemAnim;
    Image imgRedGemAnim;
    Image imgWhiteGemAnim;
    Image imgYellowGemAnim;
    Image imgBlueGemBomb;
    Image imgGreenGemBomb;
    Image imgOrangeGemBomb;
    Image imgPinkGemBomb;
    Image imgRedGemBomb;
    Image imgWhiteGemBomb;
    Image imgYellowGemBomb;
    GridPane gameGrid;

    public Label getLblScore() {return lblScore;}
    public Button getBtnNewGame() {return btnNewGame;}
    public Button getBtnOptions() {return btnOptions;}
    public Label getLblScoreBall() {return lblScoreBall;}
    public Button getBtnQuitGame() {return btnQuitGame;}
    public Label getLblTimer() {return lblTimer;}
    public GridPane getGameGrid() {return gameGrid;}

    public GameView(){
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.lblScore = new Label();
        this.btnNewGame = new Button();
        this.btnOptions = new Button();
        this.lblScoreBall = new Label();
        this.btnQuitGame = new Button();
        this.lblTimer = new Label();

        this.imgBlueGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/BlueGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgGreenGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/GreenGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgOrangeGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/OrangeGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgPinkGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/PinkGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgRedGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/RedGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgWhiteGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/WhiteGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, false);
        this.imgYellowGem = new Image(getClass().getResourceAsStream("/gameAssets/Gems/YellowGem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, false);

        this.imgBlueGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/BlueGemAnim.gif"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgGreenGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/GreenGemAnim.gif"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgOrangeGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/OrangeGemAnim.gif"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgPinkGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/PinkGemAnim.gif"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgRedGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/RedGemAnim.gif"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgWhiteGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/WhiteGemAnim.gif"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, false);
        this.imgYellowGemAnim = new Image(getClass().getResourceAsStream("/gameAssets/AnimatedGems/YellowGemAnim.gif"),50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, false);

        this.imgBlueGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/Blue_Bomb_Gem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgGreenGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/Green_Bomb_Gem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgOrangeGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/Orange_Bomb_Gem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgPinkGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/Pink_Bomb_Gem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgRedGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/Red_Bomb_Gem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, true);
        this.imgWhiteGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/White_Bomb_Gem.png"), 50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, false);
        this.imgYellowGemBomb = new Image(getClass().getResourceAsStream("/gameAssets/BombGems/Yellow_Bomb_Gem.png"),50 * SCREEN_RATIO, 50 * SCREEN_RATIO, true, false);

        this.gameGrid = new GridPane();
    }

    private void layoutNodes() {
        //Images
        Image imgNewGame = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledNewGameButton.png"), 150 * SCREEN_RATIO*1.3, 35 * SCREEN_RATIO*1.3, true, true);
        Image imgOptions = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledOptionsButton.png"), 150 * SCREEN_RATIO*1.3, 35 * SCREEN_RATIO*1.3, true, true);
        Image imgQuitGame = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledQuitGameButton.png"), 150 * SCREEN_RATIO*1.3, 35 * SCREEN_RATIO*1.3, true, true);
        Image imgScore = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledScore.png"), 183 * SCREEN_RATIO*1.3, 87 * SCREEN_RATIO*1.3, true, true);
        Image imgBejLogo = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledLogo.png"), 184 * SCREEN_RATIO*1.3, 56 * SCREEN_RATIO*1.3, true, true);
        Image imgBall = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledBall.png"), 122 * SCREEN_RATIO*1.3, 118 * SCREEN_RATIO*1.3, true, true);
        Image imgBackground = new Image(getClass().getResourceAsStream("/gameAssets/Background.png"), screenSize.getWidth(), screenSize.getHeight(), false, false);
        Image imgGrid = new Image(getClass().getResourceAsStream("/gameAssets/BejeweledClassicGrid.png"), 435 * SCREEN_RATIO, 423 * SCREEN_RATIO, true, true);

        //Background
        Background gameBackground = new Background(new BackgroundImage(imgBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT,0,false, Side.BOTTOM, 0, false), BackgroundSize.DEFAULT));

        ImageView imgLogo = new ImageView();
        imgLogo.setImage(imgBejLogo);

        lblScore.setBackground(new Background(new BackgroundImage(imgScore, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        lblScore.setMinSize(imgScore.getWidth(), imgScore.getHeight());
        lblScore.setTextAlignment(TextAlignment.CENTER);
        lblScore.setAlignment(Pos.CENTER);
        lblScore.setTextFill(javafx.scene.paint.Color.LIGHTBLUE);
        lblScore.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30*SCREEN_RATIO));
        lblScore.setText("1234");

        btnNewGame.setBackground(new Background(new BackgroundImage(imgNewGame, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        btnNewGame.setMinSize(imgNewGame.getWidth(), imgNewGame.getHeight());

        btnOptions.setBackground(new Background(new BackgroundImage(imgOptions, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        btnOptions.setMinSize(imgOptions.getWidth(), imgOptions.getHeight());

        lblScoreBall.setBackground(new Background(new BackgroundImage(imgBall, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        lblScoreBall.setMinSize(imgBall.getWidth(), imgBall.getHeight());
        lblScoreBall.setTextAlignment(TextAlignment.CENTER);
        lblScoreBall.setAlignment(Pos.CENTER);
        lblScoreBall.setTextFill(javafx.scene.paint.Color.LAVENDER);
        lblScoreBall.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30*SCREEN_RATIO));
        lblScoreBall.setText("+30");

        btnQuitGame.setBackground(new Background(new BackgroundImage(imgQuitGame, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        btnQuitGame.setMinSize(imgQuitGame.getWidth(), imgQuitGame.getHeight());

        lblTimer.setText("XX:XX");
        lblTimer.setTextAlignment(TextAlignment.CENTER);
        lblTimer.setTextFill(Color.LAVENDER);
        lblTimer.setAlignment(Pos.CENTER);
        lblTimer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30*SCREEN_RATIO));
        lblTimer.setMinWidth(imgGrid.getWidth());

        //VBox1 = BejeweledLogo and Score
        VBox mijnVBox = new VBox();
        mijnVBox.getChildren().addAll(imgLogo, lblScore);
        mijnVBox.setSpacing(-20);
        mijnVBox.setPadding(new javafx.geometry.Insets(0, 0, -20, 0));

        //VBox2 = buttons and ball
        VBox mijnVBox2 = new VBox();
        mijnVBox2.getChildren().addAll(mijnVBox, btnNewGame, btnOptions, lblScoreBall, btnQuitGame);
        mijnVBox2.setPadding(new javafx.geometry.Insets(10*SCREEN_RATIO));
        mijnVBox2.setAlignment(Pos.TOP_CENTER);
        mijnVBox2.setSpacing(5*SCREEN_RATIO);

        //Grid
        FlowPane gridImage = new FlowPane();
        gridImage.setBackground(new Background(new BackgroundImage(imgGrid, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        gridImage.setMaxHeight(imgGrid.getHeight());
        gridImage.setMaxWidth(imgGrid.getWidth());

        gameGrid.setPadding(new javafx.geometry.Insets(7*SCREEN_RATIO, 16*SCREEN_RATIO, 8*SCREEN_RATIO, 11*SCREEN_RATIO));

        gridImage.getChildren().add(gameGrid);

        //make timer show above the grid
        BorderPane gridPlusTime = new BorderPane();
        gridPlusTime.setTop(lblTimer);
        gridPlusTime.setCenter(gridImage);
        lblTimer.setPadding(new Insets(10*SCREEN_RATIO,0,-10*SCREEN_RATIO,(imgGrid.getWidth())/2));

        this.setLeft(mijnVBox2);
        this.setCenter(gridPlusTime);
        this.setBackground(gameBackground);
    }
}