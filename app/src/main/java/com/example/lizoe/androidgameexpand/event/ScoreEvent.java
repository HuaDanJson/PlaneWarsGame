package com.example.lizoe.androidgameexpand.event;

public class ScoreEvent {

    private int score;

    public ScoreEvent(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreEvent{" +
                "score=" + score +
                '}';
    }
}
