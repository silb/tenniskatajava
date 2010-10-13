package com.capgemini.sbjornse.kata.tennis;

public class Score {
    final String label;
    final Score onWin;
    final Score onLose;
    
    public Score(String label, Score onWin, Score onLose) {
        this.label = label;
        this.onWin = onWin;
        this.onLose = onLose;
    }
    
    public Score(String label, Score onWin) {
        this.label = label;
        this.onWin = onWin;
        onLose = this;
    }
    
    public Score(String label) {
        this.label = label;
        this.onWin = this;
        onLose = this;
    }

    public boolean isWinningScore() {
        return false;
    }

    public Score wonOver(Score opponent) {
        return onWin;
    }
    
    public Score lost() {
        return onLose;
    }
    
    public boolean isWinnableInNextRound(Score opponent) {
        return false;
    }

    @Override
    public String toString() {
        return label;
    }
}
