package com.capgemini.sbjornse.kata.tennis;

import java.io.PrintWriter;
import java.util.Random;

public class Game {
    
    private Score initialScore;
    private PrintWriter out;

    public Game(Score initialScore, PrintWriter out) {
        super();
        this.initialScore = initialScore;
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
        new Game(Rules.assemble(), new PrintWriter(System.out)).start();
    }
}
