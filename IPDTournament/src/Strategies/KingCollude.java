package Strategies;


// 2 is king , 1 is pawn, 0 is outsider
public class KingCollude extends PawnCollude implements Strategy {
    private Strategy tft = new Tft();

    @Override
    public String getName() {
        return "King Collude";
    };

    @Override
    public Response play(int matchCount, Response[][] history, int stratNo) {
        if (matchCount == 0){ return thecolluder.getKingCode()[0];}

        int check = colludeLogic(matchCount, history, stratNo);

        //first few rounds of the match it will broadcast its code.
        if (matchCount < thecolluder.getCodelength()){
            if (check != 0) {
                return thecolluder.getKingCode()[matchCount];
            }
        }


        if (check == 2) {
            //if opponent is recognized as a king they will cooperate mutually
            return Response.C;
        } else if (check == 1) {
            //if opponent is recognized as a pawn they will defect
            return Response.D;
        }

        //any opponent they do not recognize they will play tft as a simple strategy which prevents them from losing
        //too many points, but will also try to cooperate to maximise their points
        return tft.play(matchCount, history, stratNo);

    };
}
