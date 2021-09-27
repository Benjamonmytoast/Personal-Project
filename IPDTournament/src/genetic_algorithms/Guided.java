package genetic_algorithms;

import Strategies.Strategy;
import Tournament.Leaderboard;
import Tournament.RRLeaderboard;
import Tournament.Round_Robin;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Guided extends NaturalSelector {
    private List<XYChart.Data<Number, Number>> bestList = new ArrayList<>();
    private Leaderboard lastLeaderboard;

    public List<XYChart.Data<Number, Number>> getBestList() {
        return bestList;
    }
    public Leaderboard getLastLeaderboard(){return lastLeaderboard;}

    public  Guided(int population, int iterations, int roundsForGame, Strategy[] strategies) {


        Pair<Integer, Strategy> overallBest = new Pair<>(0, null);
        Strategy[] individuals = getInitialPopulation(population) ;

        Round_Robin tourney = new Round_Robin();
        RRLeaderboard[] results = new RRLeaderboard[population];



        for (int i = 0; i < iterations; i++) {
            RRLeaderboard geneticResults = new RRLeaderboard();

            for (int j = 0; j < population; j++) {
                Strategy[] current = addToArray(strategies, individuals[j]);

                //play tournament between the selected strategies and current candidate
                results[j] = tourney.play(current, roundsForGame);

                //List from the leaderboard used to get the ordered list of strategies based on performance
                Pair<Integer, Strategy> geneticIndividual = getGeneticStrategy(results[j].getStrategiesList(), individuals[j]);
                geneticResults.addElement(geneticIndividual.getValue(), geneticIndividual.getKey());

                if (geneticIndividual.getKey() >= overallBest.getKey()) {
                    overallBest = geneticIndividual;
                }

            }



            List<Strategy> newList = geneticResults.getGeneticList();

            //PRINTS BEST OUTCOME FOR EACH ITERATION
            //System.out.println(geneticResults.getStrategiesList().get(0).getValue().getName());

            int bestScore = geneticResults.getStrategiesList().get(0).getKey();
            bestList.add(new XYChart.Data<>(i, bestScore));

            //the evolver returns the next population from
            individuals = Evolver.breed(newList);

        }

        lastLeaderboard = tourney.play(addToArray(strategies, overallBest.getValue()), roundsForGame);

    }
    private Strategy[] addToArray(Strategy[] strategies, Strategy current) {
        Strategy[] newStrategies = new Strategy[strategies.length + 1];

        for (int i = 0; i < strategies.length; i++) {
            newStrategies[i] = strategies[i];
        }

        newStrategies[strategies.length] = current;
        return newStrategies;

    }

    private Pair<Integer, Strategy> getGeneticStrategy(List<Pair<Integer, Strategy>> newList, Strategy current) {

        for (Pair<Integer, Strategy> pair: newList) {

            if (pair.getValue().equals(current)) {
                return pair;
            }

        }

        return null;
    }
}
