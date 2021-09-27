package genetic_algorithms;

import Strategies.Strategy;
import Tournament.Leaderboard;
import Tournament.RRLeaderboard;
import Tournament.Round_Robin;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class Natural extends NaturalSelector {

    private List<XYChart.Data<Number, Number>> bestList = new ArrayList<>();
    private Leaderboard lastLeaderboard;

    public Leaderboard getLastLeaderboard(){return lastLeaderboard;}

    public List<XYChart.Data<Number, Number>> getBestList() {
        return bestList;
    }

    public  Natural(int population, int iterations, int roundsForGame) {

        Strategy[] individuals = getInitialPopulation(population) ;

        Round_Robin tourney = new Round_Robin();
        RRLeaderboard[] results = new RRLeaderboard[iterations];


        for (int i = 0; i < iterations; i++) {

            //play tournament between candidates
            results[i] = tourney.play(individuals, roundsForGame);

            //List from the leaderboard used to get the ordered list of strategies based on performance
            List<Strategy> newList = results[i].getGeneticList();

            //prints out the dna String of the best player for that iteration to console.
            //could print it to a txt file.
            //System.out.println(results[i].getStrategiesList().get(0).getValue().getName());

            //PRINTS WORST PERFORMER IN ITERATION
            //List<Pair<Integer, Strategy>> strategyList = results[i].getStrategiesList();
            //System.out.println(strategyList.get(strategyList.size() - 1).getKey());

            //PRINTS SCORE
            //System.out.println(results[i].getStrategiesList().get(0).getKey());

            // gets the best

            if (i == iterations - 1) {
                lastLeaderboard = results[i];
            }

            int bestScore = results[i].getStrategiesList().get(0).getKey();
            bestList.add(new XYChart.Data<>(i, bestScore));

            //the evolver returns the next population
            individuals = Evolver.breed(newList);

        }

    }

}
