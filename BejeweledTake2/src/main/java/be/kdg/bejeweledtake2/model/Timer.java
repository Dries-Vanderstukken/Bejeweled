package be.kdg.bejeweledtake2.model;

public class Timer {
    private final int START_TIME = 300;
    private int currentTime;
    private boolean running = false;
    private Game associatedGame;

    public Timer(Game associatedGame){
        this.associatedGame = associatedGame;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void startTimer() {
        running = true;
        double startTime = System.currentTimeMillis();

        do {
            double elapsedTime = System.currentTimeMillis() - startTime;
            double elapsedSeconds = elapsedTime / 1000;
            currentTime -= elapsedSeconds;
        } while(running && (currentTime > 0));
        associatedGame.gameOver();
    }
    public void stopTimer() {
        running = false;
    }
}
