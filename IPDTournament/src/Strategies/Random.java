package Strategies;

public class Random implements Strategy {

    public Response play(int matchCount, Response[][] history, int stratNo) {

        java.util.Random random = new java.util.Random();
        return random.nextBoolean() ? Response.C:Response.D;    //randomly chooses one option
    }

    public String getName() {
        return "Random 50:50";
    }
}
