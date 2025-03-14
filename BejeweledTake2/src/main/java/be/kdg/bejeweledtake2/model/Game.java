package be.kdg.bejeweledtake2.model;

public class Game {
    private Tile[][] playingField;
    private Timer timer;
    private Score score;

    // When a new game is created we must give it a score and a timer
    public Game() {
        this.playingField = new Tile[8][8];
        this.score = new Score();
        this.score.setScore(0);
        randomize();
    }

    public void randomize(){
        System.out.println("randomizing");
        for (int i = 0; i < playingField.length; i++) {
            System.out.println(i);
            for (int j = 0; j < playingField[i].length; j++) {
                playingField[i][j] = new Tile();
                playingField[i][j].setGem(new Gem(GemColor.BLUE,GemMutation.NONE));
            }
        }
        System.out.println("done");
    }

    public void checkSurroundings(int x, int y){
        for (Tile[] row :playingField){
            for (Tile tile : row){

            }
        }
    }
}
