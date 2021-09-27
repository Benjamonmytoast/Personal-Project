package genetic_algorithms;

import Strategies.Response;
import Tournament.Leaderboard;
import javafx.scene.chart.XYChart;

import java.util.List;

public abstract class NaturalSelector {

    public abstract List<XYChart.Data<Number, Number>> getBestList();
    public abstract Leaderboard getLastLeaderboard();

    protected Response getRandom() {
        java.util.Random random = new java.util.Random();
        return random.nextBoolean() ? Response.C : Response.D;
    }

    protected Candidate[] getInitialPopulation(int population) {
        Candidate[] strategies = new Candidate[population];
        Dna.setDepth(4);

        //for each member of the initial population
        for (int member = 0; member < population; member++) {

            Dna myDna = new Dna();
            //creates dna for each candidate and assigns that dna to the candidate.
            for (int level = 0; level < Dna.getDepth(); level++) {

                for (int actual = 0; actual < Math.pow(2, (2*level)); actual++) {
                    myDna.setDna(level, actual, getRandom());
                    strategies[member] = new Candidate(myDna);
                }

            }
        }

        return strategies;

    }
}
