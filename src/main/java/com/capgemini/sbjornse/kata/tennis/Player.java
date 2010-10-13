package com.capgemini.sbjornse.kata.tennis;

public class Player {
    
    final String name;
    final Score score;
    
    public Player(String name, Score score) {
        super();
        this.name = name;
        this.score = score;
    }
    
    public Player(Player player, Score score) {
        this(player.name, score);
    }

    @Override
    public String toString() {
        return name + " " + score;
    }

}
