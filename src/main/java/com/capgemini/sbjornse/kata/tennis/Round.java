package com.capgemini.sbjornse.kata.tennis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Round {
    
    private final Random randomSource;
    private final Player playerA;
    private final Player playerB;
    
    public static final class FinalRound extends Round {
        
        public FinalRound(Player playerA, Player playerB) {
            super(playerA, playerB);
        }

        @Override
        boolean isPlayable() {
            return false;
        };
    }
    
    private Round(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        randomSource = null;
    }
    
    public Round(Random randomSource, Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.randomSource = randomSource;
        assertNoWinner(playerA, playerB);
    }
    
    private static void assertNoWinner(Player playerA, Player playerB) throws IllegalStateException {
        if (playerA.score.isWinningScore() || playerB.score.isWinningScore()) {
            throw new IllegalStateException("Not playable: " + playerA + " " + playerB);
        }
    }

    
    /**
     * Play this round
     * 
     * @return the next round
     */
    Round play() {
        boolean winnerIsPlayerA = randomSource.nextBoolean();
        
        Player winner = null;
        Player looser = null;
        
        if (winnerIsPlayerA) {
            winner = new Player(playerA, playerA.score.wonOver(playerB.score));
            looser = new Player(playerB, playerB.score.lost());
        } else {
            winner = new Player(playerB, playerB.score.wonOver(playerA.score));
            looser = new Player(playerA, playerA.score.lost());
        }
        
        return gameContinues(winner.score, looser.score) ? new Round(randomSource, winner, looser) : new FinalRound(winner, looser);
    }

    boolean isPlayable() {
        return true;
    }
    
    private static boolean gameContinues(Score a, Score b) {
        return !a.isWinningScore() &&  !b.isWinningScore();
    }
    
    @Override
    public String toString() {
        Player[] players = { playerA, playerB };
        Arrays.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.name.compareTo(player2.name);
            }
        });
        
        String gameStateDescription;
        
        if (playerA.score != playerB.score) {
            gameStateDescription = players[0] + " - " + players[1];
        } else if (playerA.score instanceof ShortName) {
            gameStateDescription = ((ShortName) playerA.score).nameWhenScoreIsAll();
        } else {
            gameStateDescription = "All " + playerA.score;
        }
        
        boolean playerACanWinInNextRound = playerA.score.isWinnableInNextRound(playerB.score);
        boolean playerBCanWinInNextRound = playerB.score.isWinnableInNextRound(playerA.score);
        boolean setBall =  playerACanWinInNextRound || playerBCanWinInNextRound;
        
        String setBallMessage = (setBall ? ", Set ball " + (playerACanWinInNextRound ? playerA.name : playerB.name) : "");
            
        return gameStateDescription + setBallMessage;
    }

}
