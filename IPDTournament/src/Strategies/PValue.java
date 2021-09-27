package Strategies;

import java.util.Random;

public class PValue implements Strategy {

    private float pval = new Random().nextFloat();

    public Response play(int matchCount, Response[][] history, int stratNo) {
        return calcPval();
    }

    private Response calcPval() {
        double random_double = Math.random();

        return random_double < pval ? Response.C:Response.D;    //randomly chooses one option
    }

    public String getName() {
        return "PValue " + pval;
    }
}

