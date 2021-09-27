package Strategies;

import javafx.util.Pair;

public class PMatrix {

    //represents payoff matrix
    private int T;//you D opp C
    private int R;//you C opp C
    private int P;//you D opp D
    private int S;//you C opp D

    private static PMatrix ourInstance = new PMatrix();

    public static PMatrix getInstance() {
        return ourInstance;
    }

    private PMatrix() {
        T = 4;
        R = 3;
        P = 2;
        S = 1;
    }

    //method to update matrix values if needed
    public void updateTRPS(int t, int r, int p, int s){
        T = t;
        R = r;
        P = p;
        S = s;
    }

    //this method was implemented directly into match, but makes more sense here.
    public Pair score(Response aGo, Response bGo) {


        if (aGo == Response.C) {
            if (bGo == Response.C) {
                return new Pair<>(R, R);// you C opp C
            } else {
                return new Pair<>(S, T);// you C opp D

            }
        } else {
            if (bGo == Response.C) {
                return new Pair<>(T, S);// you D opp C
            } else {
                return new Pair<>(P, P);// you D opp D
            }
        }
    }

    //return the values
    public int getT() {return T;}
    public int getR() {return R;}
    public int getP() {return P;}
    public int getS() {return S;}

}
