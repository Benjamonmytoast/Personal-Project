package genetic_algorithms;
import Strategies.Response;
import Strategies.StratFactory;
import Strategies.Strategy;
import Tournament.Tournament;
import Tournament.TourneyFactory;
import Tournament.Leaderboard;

import javafx.scene.chart.XYChart;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DnaTest {
    int depth = 5;
    Dna myDna = new Dna();

    @Before
    public void setup() {



        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < Math.pow(2, (2*i)); j++) {
                myDna.setDna(i, j, getRandom());
            }
        }
    }

    @Test
    public void testDna() {



        for (int i = 0; i < depth; i++) {
            System.out.print("[");
            for (int j = 0; j < Math.pow(2, (2*i)); j++) {
                System.out.print(myDna.getDna(i, j)+ ", ");
            }
            System.out.println("]");
        }


        assertTrue(true);
    }

    private Response getRandom() {
        java.util.Random random = new java.util.Random();
        return random.nextBoolean() ? Response.C:Response.D;
    }

    @Test
    public void testTourneyWithGen() {
        TourneyFactory factory = new TourneyFactory();
        StratFactory x = new StratFactory();

        Tournament rR = factory.getTourney("RR");

        Strategy myCandidate = new Candidate(myDna);

        Strategy[] strategies = new Strategy[6];
        strategies[0] = myCandidate;
        strategies[1] = x.getStrategy("TFT");
        strategies[2] = x.getStrategy("Random");
        strategies[3] = x.getStrategy("Random");
        strategies[4] = x.getStrategy("Random");
        strategies[5] = x.getStrategy("TFT");

        int rounds = 20;

        Leaderboard leaderboard = rR.play(strategies, rounds);
        System.out.println(leaderboard.getLeaderboardList().toString());

    }

    @Test
    public void testNatural() {

        int population = 40;
        int iterations = 200;
        Strategy[] strategies = new Strategy[5];
        List<XYChart.Data<Number, Number>> result;
        Natural theNatural = new Natural(population, iterations, 40);
        result = theNatural.getBestList();

    }

}
