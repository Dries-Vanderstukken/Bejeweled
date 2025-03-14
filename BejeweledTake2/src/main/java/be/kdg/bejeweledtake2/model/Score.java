package be.kdg.bejeweledtake2.model;

public class Score {
    private int score;
    private String playerName;

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Score() {
        score = 0;
    }

    public void incrementScore(int i) {
        score += i;
    }
}
