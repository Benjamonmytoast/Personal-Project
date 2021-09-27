package Tournament;

import Strategies.Match;
import Strategies.Response;
import Strategies.Strategy;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class FairGame {
    private Match[] matches;
    private int indexTotal;

    public FairGame(Strategy stratA, Strategy stratB, int numOfTotalRounds, int numOfMatches) {
        matches = new Match[numOfMatches];
        int[] rounds = getRounds(numOfTotalRounds, numOfMatches);
        this.indexTotal = numOfMatches;

        for (int i = 0; i < numOfMatches; i++) {
            matches[i] = new Match(stratA, stratB, rounds[i]);
        }
    }


    //creates an array of integers which represent the number of rounds per match.
    private int[] getRounds(int total, int num) {
        int[] rounds = new int[num];
        int whatsLeft = total;

        //each integer in the array is calculated randomly and the bounds change at each iteration to ensure the total of rounds is always the same.
        for (int i = 0; i < num - 1; i++) {
            rounds[i] = ThreadLocalRandom.current().nextInt(1, whatsLeft - (num - i));
            whatsLeft -= rounds[i];
        }

        rounds[num - 1] = whatsLeft;

        return rounds;

    }

    public Match[] getMatches() {
        return matches;
    }

    public Strategy getStrategyA()  {

        return matches[0].getStrategyA();
    }
    public Strategy getStrategyB() {

        return matches[0].getStrategyB();
    }

    //returns average score across all games for each strategy.
    public int getStratAScore()  {
        int total = 0;
        for (Match match:matches) {
            total += match.getStratAScore();
        }

        return Math.floorDiv(total, indexTotal );
    }
    public int getStratBScore() {
        int total = 0;
        for (Match match:matches) {
            total += match.getStratBScore();
        }

        return Math.floorDiv(total, indexTotal );

    }
}
