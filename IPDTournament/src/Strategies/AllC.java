package Strategies;

public class AllC implements Strategy {

    //always cooperates
    public Response play(int matchCount, Response[][] history, int stratNo) {return Response.C;}

    public String getName(){
        return "Always Cooperate";
    }
}
