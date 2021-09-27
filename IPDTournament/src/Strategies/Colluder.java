package Strategies;


public class Colluder {
    private final int codelength = 5;
    private final Response[] pawnCode;
    private final Response[] kingCode;


    //needed as singleton to ensure it can only ever be created once.
    private static Colluder ourInstance = new Colluder();

    public static Colluder getInstance() {
        return ourInstance;
    }

    private Colluder() {
        pawnCode = getCode();
        kingCode = getCode();
    }

     private Response[] getCode () {
        //initialise codes, random for now, may be better to find codes that are not optimal in a game scenario
        //i.e. less likely to be played by an intelligent strategy.
         Response[] code = new Response[codelength];
         java.util.Random random = new java.util.Random();

         for (int i = 0; i < codelength; i++) {
             code[i] = random.nextBoolean() ? Response.C:Response.D;
         }

         return code;
    }
    public Response[] getPawnCode() {
        return pawnCode;
    }
    public Response[] getKingCode() {
        return kingCode;
    }

    public int getCodelength() {
        return codelength;
    }
}
