package com.capgemini.sbjornse.kata.tennis;

import java.io.PrintWriter;
import java.util.Random;

public class Game {
    
    private final Score initialScore;
    private final PrintWriter out;

    public Game(PrintWriter out) {
        super();
        this.initialScore = Rules.assemble();
        this.out = out;
    }

    void start() {
        Round currentRound = new Round(
                new Random(),
                new Player("Tux", initialScore),
                new Player("Puffy", initialScore));
        
        out.println(currentRound);
        
        while (currentRound.isPlayable()) {
            currentRound = currentRound.play();
            out.println(currentRound);
        }
        
        out.println();
        out.flush();
    }
    
    public static void main(String[] args) {
        new Game(new PrintWriter(System.out)).start();
    }
}
