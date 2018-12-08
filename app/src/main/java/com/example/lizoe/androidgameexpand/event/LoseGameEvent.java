package com.example.lizoe.androidgameexpand.event;

public class LoseGameEvent {

    private int type;//1 代表胜利  2 代表失败

    private int loseScore;

    public LoseGameEvent(int type, int loseScore) {
        this.type = type;
        this.loseScore = loseScore;
    }

    public int getLoseScore() {
        return loseScore;
    }

    public void setLoseScore(int loseScore) {
        this.loseScore = loseScore;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoseGameEvent{" +
                "type=" + type +
                ", loseScore=" + loseScore +
                '}';
    }
}
