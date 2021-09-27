package Strategies;

import javafx.util.Pair;

public class ScoreAdapt implements Strategy {
    private final PMatrix payoff = PMatrix.getInstance();


    public Response play(int matchCount, Response[][] history, int stratNo) {
        int myScore = 0;
        int theirScore = 0;

        //goes through history, calculates score for each strategy
        for (int i = 0; i < matchCount; i++) {
            Pair<Integer, Integer> thePair = payoff.score(history[stratNo][i], history[1 - stratNo][i]);
            myScore += thePair.getKey();
            theirScore += thePair.getValue();
        }

        //response is based on score.
        return choice(myScore, theirScore);

    }

    //if the strategy is ahead or equal it will cooperate, if it is behind it will defect.
    private Response choice(int myScore, int theirScore) {
        if (myScore >= theirScore) {
            return Response.C;
        } else {
            return Response.D;
        }
    }


    public String getName() {
        return "ScoreAdapt";
    }
}
