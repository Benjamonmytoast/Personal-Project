package Tournament;

import Strategies.Match;
import Strategies.Strategy;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Single_Elimination implements Tournament {

    public Leaderboard play(Strategy[] strategies, int notUsing)  {
        SELeaderboard leaderboard = new SELeaderboard();

        //the final winner is added separately so "Winner" is put next to their name.
        leaderboard.addElement(singleElimRec(strategies, leaderboard).getName());
        return leaderboard;

    }


    //returns a sub array between beginning (INC) and end (EXC)
    private Strategy[] subArray(Strategy[] strategies, int beg, int end) {
        return Arrays.copyOfRange(strategies, beg, end);
    }


    //recursively defining the single elimination tree(in the case of draw Strategy B is returned).
    private Strategy singleElimRec(Strategy[] strategies, SELeaderboard leaderboard) {
        Match theMatch;

        //base case is when there are only two strategies, they play and the winner is returned.
        if (strategies.length == 2) {
            theMatch = new Match(strategies[0], strategies[1], ThreadLocalRandom.current().nextInt(roundsLower, roundsUpper));

            //match is added to the leaderboard
            leaderboard.addElement(theMatch.getStrategyA().getName(),theMatch.getStrategyB().getName() );

            //returns the winner
            return (theMatch.getStratAScore() > theMatch.getStratBScore())? theMatch.getStrategyA():theMatch.getStrategyB();
        }

        int halfWay = strategies.length / 2;

        //sub lists are created, splitting the strategies into two pools, which continues until every strategy is paired with only one other.
        Strategy[] strategyListOne = subArray(strategies, 0, halfWay);
        Strategy[] strategyListTwo = subArray(strategies, halfWay, strategies.length);

        //recursively creating a match of the winners of the level below, then returning the strategy that wins
        theMatch = new Match(singleElimRec(strategyListOne, leaderboard), singleElimRec(strategyListTwo, leaderboard), ThreadLocalRandom.current().nextInt(roundsLower, roundsUpper));

        //Leaderboard is updated with the results of the match
        leaderboard.addElement(theMatch.getStrategyA().getName(),theMatch.getStrategyB().getName() );

        //winner is returned
        return (theMatch.getStratAScore() > theMatch.getStratBScore())? theMatch.getStrategyA():theMatch.getStrategyB();
    }

}
