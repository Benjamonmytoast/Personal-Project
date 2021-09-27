package Strategies;

import java.util.Arrays;


public class PawnCollude implements Strategy {
    protected Colluder thecolluder = Colluder.getInstance();

    public Response play(int matchCount, Response[][] history, int stratNo) {
        if (matchCount == 0){ return thecolluder.getPawnCode()[0];}

        int check = colludeLogic(matchCount, history, stratNo);

        //code broadcasted
        if (matchCount < thecolluder.getCodelength()){
            if (check != 0) {
                return thecolluder.getPawnCode()[matchCount];
            }
        }

        //if opponent is a fellow pawn or a king they will cooperate.
        //when opponent is also a pawn outcome will be CC
        //when opponent is a king they will get the suckers payoff, but boost the king
        if ((check == 1) || (check == 2)) {
            return Response.C;
        }

        //they will defect against all random opponents in an attempt to minimise their score.
        return Response.D;

    };

    public String getName() {
        return "Pawn Collude";
    };

    // 2 is king , 1 is pawn, 0 is outsider
    protected int colludeLogic(int matchCount, Response[][] history, int stratNo) {

        //max the depth at the length of the code
        int depth = matchCount;
        if (matchCount > thecolluder.getCodelength()) {depth = thecolluder.getCodelength();}

        //get the subarray of the opponents history
        Response[] checker = Arrays.copyOfRange(history[1-stratNo], 0, depth);

        //if the opponents history equals either one of the king or pawn codes then it will return 2 or 1
        if (Arrays.equals(checker, Arrays.copyOfRange(thecolluder.getKingCode(), 0, depth))) {
            return 2;
        }else if (Arrays.equals(checker, Arrays.copyOfRange(thecolluder.getPawnCode(), 0, depth))) {
            return 1;
        }
        //if the opponent is a stranger
        return 0;

    }
}
