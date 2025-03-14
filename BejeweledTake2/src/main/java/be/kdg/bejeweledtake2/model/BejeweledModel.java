package be.kdg.bejeweledtake2.model;

public class BejeweledModel {

    private Game currentGame;

    public BejeweledModel(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

}