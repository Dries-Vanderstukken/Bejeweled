package be.kdg.bejeweledtake2.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameTimer {
    private Game associatedGame;
    private boolean running = false;
    private final int START_TIME = 300;
    private long currentTime = START_TIME;

    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
        currentTime--;
        if (currentTime < 0){
            running = false;
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

    public void startTimer(){
        running = true;
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }
    public void stopTimer() {
        running = false;
        timer.stop();
    }
}
