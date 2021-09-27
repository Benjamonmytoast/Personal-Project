package Strategies;

import javafx.util.Pair;

import java.util.Arrays;

public class Match {

    private PMatrix payoff = PMatrix.getInstance();// payoff matrix so reward values are set
    private Response[][] history;// records history of the match so that the strategies can see previous plays

    private int[][] scores;// keeps score for each round for each strategy
    private int matchCount;// matchCount kept outside to avoid passing through all method calls as its needed in most

    private Strategy strategyA, strategyB;

    public Match(Strategy stratA, Strategy stratB, int length) {
        //instantiates array lengths based on defined match 'length'
        history = new Response [2] [length];
        scores = new int [2] [length];
        Response aGo;
        Response bGo;
        strategyA = stratA;
        strategyB = stratB;

        //loops through each round from round 0 to length
        for (matchCount = 0; matchCount < length; matchCount++) {

            //each strategy has there turns, using matchCount and match history to strategise
            aGo = stratA.play(matchCount, history, 0);
            bGo = stratB.play(matchCount, history, 1);

            //score is calculated then updated
            Pair<Integer, Integer>  thePair = payoff.score(aGo, bGo);
            updateScores(thePair.getKey(), thePair.getValue());

            //history is updated for next round
            updateHistory(aGo, bGo);

        }
    }

    public Response[][] getHistory() {
        return history;
    }

    public int[] getAllAScores() {
        return scores[0];
    }
    public int[] getAllBScores() {
        return scores[1];
    }

    public Strategy getStrategyA() {return strategyA;}
    public Strategy getStrategyB() {return strategyB;}

    //returns totalled scores for each strategy.
    public int getStratAScore() {
        return Arrays.stream(scores[0]).sum();
    }
    public int getStratBScore() {
        return Arrays.stream(scores[1]).sum();
    }

    private void updateScores(int a, int b) { //updates score array for the scores from the current round
        scores[0][matchCount] = a;
        scores[1][matchCount] = b;
    }

    private void updateHistory(Response a, Response b) { //updates history array with the responses made by each person

        history[0][matchCount] = a;
        history[1][matchCount] = b;
    }
}
