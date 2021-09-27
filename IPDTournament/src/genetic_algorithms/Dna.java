package genetic_algorithms;

import Strategies.Response;

import java.util.ArrayList;

public class Dna {
    private ArrayList<Response []> wholeDna = new ArrayList<>();
    private static int depth = 4;

    public Dna() {

        //initialises list of response arrays to size relative to depth.
        for (int i = 0; i < depth; i++) {
            wholeDna.add(new Response[(int) Math.pow(2, (2*i))]);
        }
    }
    public void setDna(int whole, int sub, Response e) {
        wholeDna.get(whole)[sub] = e;
    }

    public Response getDna(int whole, int sub) {
        if (wholeDna.get(whole).length < sub) return null;
        return wholeDna.get(whole)[sub];
    }
    public static int getDepth() {
        return depth;
    }

    public static void setDepth(int myDepth) {
         depth = myDepth;
    }

}
