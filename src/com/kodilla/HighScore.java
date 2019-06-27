package com.kodilla;

import java.io.Serializable;

public class HighScore implements Serializable {

    private String playerName;
    private int scores;

    public HighScore(String playerName, int scores) {
        this.playerName = playerName;
        this.scores = scores;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScores() {
        return scores;
    }
}
