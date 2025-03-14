package be.kdg.bejeweledtake2.model;

import be.kdg.bejeweledtake2.view.game.GamePresenter;
import be.kdg.bejeweledtake2.view.game.GameView;
import java.util.Random;

public class Game {
    private Tile[][] playingField;
    private Timer timer;
    private Score score;



    // When a new game is created it gets a timer, a score and a playing field (which gets randomized after)
    public Game() {
        this.playingField = new Tile[8][8];
        this.timer = new Timer();
        this.score = new Score();
        this.score.setScore(0);
        randomize();
        // Under this is testing
        // checkSurroundings(5,5);
        printField();
        System.out.println("RANDOM COLOUR HERE:" + colorRandomizer());
        checkAlreadyMatching(playingField[5][5]);
    }

    public GemColor colorRandomizer(){
        GemColor randomColor = GemColor.NONE;
        while(randomColor == GemColor.NONE){
            randomColor = GemColor.values()[(int)(Math.random()*8)];
        }
        return randomColor;
    }

    public void randomize(){
        System.out.println("randomizing");
        for (int i = 0; i < 8; i++) {
            System.out.println(i);
            for (int j = 0; j < 8; j++) {
                System.out.println(i+" "+j);
                playingField[i][j] = new Tile(TileStatus.NONE,i,j);
                playingField[i][j].setGem(new Gem(colorRandomizer(),GemMutation.NONE));
                System.out.println(playingField[i][j].getGem().getGemColor());
            }
        }
        System.out.println("done");
    }

    public void checkAlreadyMatching(Tile checkingTile){
        System.out.println(checkingTile.getGem().getGemColor());
        Integer[][] matches;
        matches = new Integer[64][2];

        for (Tile[] column :playingField){
            for (Tile tile : column){
                if(tile.getGem().getGemColor() == checkingTile.getGem().getGemColor()){
                    System.out.println("We've got one of the same colour! It's in row " + tile.getRow() + " in column " + tile.getColumn() );
                    matches[1] = new Integer[]{1, 2};

                    if(tile.getRow() == checkingTile.getRow()){
                        System.out.println("We've got one of the same color in the same row here at row " + checkingTile.getRow());
                    }
                    if(tile.getColumn() == checkingTile.getColumn()){
                        System.out.println("We've got one of the same color in the same column here at column " + checkingTile.getColumn());
                    }
                }
            }
        }
    }

    public void checkSurroundings(int x, int y){
        Tile checkingTile = playingField[x][y];
        for (Tile[] column : playingField){
            for (Tile currentTile : column){
                System.out.println("Checking column "+ currentTile.getColumn() +" Row " + currentTile.getRow());
                if(currentTile.getGem().getGemColor() == checkingTile.getGem().getGemColor()){
                    System.out.println("We've got one of the same colour! It's in row " + currentTile.getRow() + " on tile " + currentTile.getColumn() );
                }
            }
        }
    }

    public void printField(){
        System.out.println("------Field------");
        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField[i].length; j++) {

                System.out.print(playingField[i][j].getGem().getGemColor() + " \t \t");
            }
            System.out.println("\n");
        }
    }




    public Tile[][] getPlayingField() {
        return playingField;
    }

    public void tileClicked(int x,int y){
        playingField[x][y].setStatus(TileStatus.SELECTED);
    }
}
