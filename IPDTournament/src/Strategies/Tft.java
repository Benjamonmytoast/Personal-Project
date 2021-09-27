package Strategies;

public class Tft implements Strategy {

    //uses tit for tat method
    public Response play(int matchCount, Response[][] history, int stratNo) {

        //cooperate on first go
        if (matchCount == 0) {return Response.C;}


        //do same as opponent in the last round
        return history[1 - stratNo][matchCount - 1];
    }

    public String getName() {
        return "Tit-for-tat";
    }
}
