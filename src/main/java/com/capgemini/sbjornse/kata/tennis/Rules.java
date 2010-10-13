package com.capgemini.sbjornse.kata.tennis;

public class Rules {
    
    /**
     * Assemble the default tennis rules
     * @return the initial score of a player
     */
    static Score assemble() {
        
        final Score winner = new Score("Winner") {
            @Override
            public boolean isWinningScore() {
                return true;
            }
        };
        
        // Some scores depend on each other, but not at construction time.
        final Score[] advantageRef = new Score[1];
        
        class FourtyScore extends Score implements ShortName {
            
            FourtyScore() {
                super("Fourty");
            }
            
            @Override
            public Score wonOver(Score opponent) {
                if (opponent == this) {
                    return advantageRef[0];
                } else if (opponent == advantageRef[0]) {
                    return this;
                }
                return winner;
                
            }
            
            @Override
            public boolean isWinnableInNextRound(Score opponent) {
                return this != opponent && opponent != advantageRef[0];
            }

            @Override
            public String nameWhenScoreIsAll() {
                return "Deuce";
            }
        };
        
        final Score fourty = new FourtyScore();
            
        
        final Score advantage = new Score("Advantage", winner, fourty) {
            @Override
            public boolean isWinnableInNextRound(Score opponent) {
                return true;
            }
        };
        advantageRef[0] = advantage;
        
        final Score thirty = new Score("Thirty", fourty);
        final Score fifteen = new Score("Fifteen", thirty);
        final Score zero = new Score("Love", fifteen);
        
        return zero;
    }

}
