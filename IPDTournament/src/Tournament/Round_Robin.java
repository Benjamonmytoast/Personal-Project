package Tournament;

import Strategies.*;
import java.util.concurrent.ThreadLocalRandom;

public class Round_Robin implements Tournament {

    public RRLeaderboard play(Strategy[] strategies, int roundsForGame) {
        RRLeaderboard leaderboard = new RRLeaderboard();
        int size = strategies.length;
        int numOfMatches = 3;

        //rounds can be chose to be random if user defines 0.
        if (roundsForGame==0) {
            roundsForGame = ThreadLocalRandom.current().nextInt(roundsLower, roundsUpper);
        } else {
            roundsForGame = roundsForGame * numOfMatches;
        }

        //FairGame is used here instead of match as fair game produces multiple matches between two strategies,
        // with random rounds for each match but all games in the rounds robin will play the same number of rounds.
        FairGame[][] fairGame = new FairGame[size][size];

        //instantiates and plays matches between strategies all strategies except themselves.
        for (int i = 0; i < size; i++){
            for (int j = 0; j < i; j++) {
                fairGame[i][j] = new FairGame(strategies[i], strategies[j], roundsForGame, numOfMatches);
            }
        }

        int[] totals = new int[size];
        //prints out results of games.
        for (int i = 0; i < size; i++){

            for (int j = 0; j < i; j++) {
                totals[i] += fairGame[i][j].getStratAScore();
                totals[j] += fairGame[i][j].getStratBScore();
            }

        }

        //Creates leaderboard and the leaderboard is returned
        leaderboard.addRounds((int)Math.floor(roundsForGame/numOfMatches));
        for (int i = 0; i < size; i++) {
            leaderboard.addElement(strategies[i], totals[i] );
        }

        return leaderboard;
    }
}
