package genetic_algorithms;

import Strategies.Response;
import Strategies.Strategy;

public class Candidate implements Strategy {
    private Dna myDna;


    public Candidate(Dna myDna) {
        this.myDna = myDna;
    }

    public Dna getDna() {
        return myDna;
    }

    public Response play(int matchCount, Response[][] history, int stratNo){

        int depth;
        //sets depth to matchcount, unless the matchount is higher than the max depth of the dna.
        //dna stores two dimensional array, depth corresponds to the index of the final sub array.
        //i.e with max depth 5, depth = 4.
        if (matchCount >= Dna.getDepth() - 1) {
            depth = Dna.getDepth() - 1;
        } else {
            depth = matchCount;
        }

        return generateResponse(getList(matchCount, depth, history, stratNo));
    }

    public String getName() {
        String name = "";
        //goes through whole dna and writes it as one long string.
        //useful for analysing development
        //MUST CHANGE IF BETTER OPTION

        for (int i = 0; i < Dna.getDepth(); i++) {
            name += " : ";
            for (int j = 0; j < Math.pow(2, (2*i)); j++) {
                name += myDna.getDna(i, j).name();
            }
        }

        /*name = "genetic algorithm";*/
        return name;
    }

    //returns the relevant history for the current depth of the dna and matchount
    private Response[] getList(int matchcount, int depth, Response[][] history, int stratNo) {

        Response[] list = new Response[2 * depth];
        int relevantHistory = matchcount - depth;


        int j = 0;
        for (int i = 0; i < (list.length); i = i + 2) {
            list[i] = history[stratNo][relevantHistory + j];
            list[i+1] = history[1-stratNo][relevantHistory + j];
            j++;
        }

        return list;
    }

    private Response generateResponse(Response[] list) {
        //convert list to binary
        //convert binary to decimal
        int listSize = list.length;

        //convert list to boolean array, representing binary C = 0, D = 1
        boolean[] boolArray = new boolean[listSize];

        for (int i = 0; i < listSize; i++) {
            boolArray[i] = list[i].equals(Response.D);
        }

        //convert binary to appropriate
        int value = 0;
        for (int i = 0; i < listSize ; ++i) {
            value = (value << 1) + (boolArray[i] ? 1 : 0);
        }

        return myDna.getDna(listSize/2, value);

    }
}
