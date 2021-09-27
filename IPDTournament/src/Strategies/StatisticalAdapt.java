package Strategies;

public class StatisticalAdapt implements Strategy {
    private final int k = 5;
    private final int n = 10;
    private final int cooperateChange = 1;
    private final int defectChange = 2;


    public Response play(int matchCount, Response[][] history, int stratNo) {
        return Choice( getProb(history, stratNo, matchCount));
    }


    //The probability to cooperate increases by 1 if CC or DD, decreases by 2 if CD or DC.
    private int getProb(Response[][] history, int stratNo, int matchCount) {
        int change = 0;

        for (int i = 0; i < matchCount; i++) {
            if (increase(history[stratNo][i], history[1 - stratNo][i])) {
                change += cooperateChange;
            } else {
                change -= defectChange;
            }
        }

        //ensures that probability will be between 0 and 1.
        if (k + change > n) {
            return 10;
        } else if (k + change < 0) {
            return 0;
        } else {
            return (k + change);
        }
    }

    //defining when to increase, when not increase, decrease.
    private boolean increase(Response me, Response you) {
        if ((me == Response.C) && (you == Response.C)) {
            return true;
        } else if ((me == Response.D) && (you == Response.D)) {
            return true;
        }
        return false;
    }

    private Response Choice(int prob) {
        double random_double = Math.random();
        return random_double < ((double) prob / n) ? Response.C:Response.D;
    }


    public String getName() {
        return "Statistical Adapt";
    }
}
