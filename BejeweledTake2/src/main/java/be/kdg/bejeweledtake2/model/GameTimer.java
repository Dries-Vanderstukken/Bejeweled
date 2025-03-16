package be.kdg.bejeweledtake2.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameTimer {
    private Game associatedGame;
    private final int START_TIME = 60;
    private long currentTime = START_TIME;

    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
        currentTime--;
        if (currentTime > START_TIME) {
            currentTime = START_TIME;
        }

        if (currentTime < 0){
            associatedGame.gameOver();
            stopTimer();
        }
        associatedGame.getPresenter().updateTimer(currentTime);
    }));

    public GameTimer(Game associatedGame){
        this.associatedGame = associatedGame;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void incrementTime(long increment){
        currentTime += increment;
    }

    public void startTimer(){
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }
    public void stopTimer() {
        timer.stop();
    }
}
