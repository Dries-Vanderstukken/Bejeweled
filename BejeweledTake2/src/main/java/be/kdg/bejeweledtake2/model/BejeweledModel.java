package be.kdg.bejeweledtake2.model;

public class BejeweledModel {

    private Game currentGame;

    public Game getCurrentGame() {
        return currentGame;
    }

    public void newGame() {
         currentGame = new Game();
    }

}