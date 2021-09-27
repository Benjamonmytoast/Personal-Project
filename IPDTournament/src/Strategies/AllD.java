package Strategies;

public class AllD implements Strategy {

    //always defects
    public Response play(int matchCount, Response[][] history, int stratNo) {
        return Response.D;
    }

    public String getName(){
        return "Always Defect";
    }
}