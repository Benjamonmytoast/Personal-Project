package Strategies;

public class Pavlov implements Strategy {

    public Response play(int matchCount, Response[][] history, int stratNo) {
        if (matchCount == 0) {return Response.C;}   //when there is no history established, begin with cooperation.

        Response prevMove = history[stratNo][matchCount - 1];
        Response oppPrevMove = history[1 - stratNo][matchCount - 1];

        if(win(prevMove, oppPrevMove)) {     //if win, repeat last move
            return prevMove;

        } else {                                    //if lose, do opposite move
            if (prevMove == Response.C) {
                return Response.D;
            } else {
                return Response.C;
            }

        }
    }

    private boolean win(Response prevMove, Response oppPrevMove) {

        return ((prevMove == Response.C) &&     //if mutual cooperation was achieved on the previous turn R.
                        (oppPrevMove == Response.C)) ||

                ((prevMove == Response.D) &&    //if Strategies.Pavlov got T the temptation, Strategies.Pavlov defected and opponent cooperated.
                        (oppPrevMove == Response.C));
    }

    public String getName() {
        return "Pavlov";
    }
}
