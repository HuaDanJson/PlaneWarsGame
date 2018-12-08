package com.example.lizoe.androidgameexpand.event;

public class LoseGameEvent {

    private int loseScore;

    public LoseGameEvent(int loseScore) {
        this.loseScore = loseScore;
    }

    public int getLoseScore() {
        return loseScore;
    }

    public void setLoseScore(int loseScore) {
        this.loseScore = loseScore;
    }

    @Override
    public String toString() {
        return "LoseGameEvent{" +
                "loseScore=" + loseScore +
                '}';
    }
}
