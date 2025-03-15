package be.kdg.bejeweledtake2.model;

public class Timer {
    private final int START_TIME = 300;
    private int currentTime;

    public int getCurrentTime() {
        return currentTime;
    }

    public void startTimer(){
        currentTime = START_TIME;
    }
}
