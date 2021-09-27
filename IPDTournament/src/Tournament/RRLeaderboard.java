package Tournament;

import Strategies.Strategy;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RRLeaderboard implements Leaderboard {
    private List<Pair<Integer, Strategy>> strategiesList = new ArrayList<>();
    private List<String> leaderboardList = new ArrayList<>();
    private List<Strategy> geneticList = new ArrayList<>();
    private int rounds;

    //returns the leaderboard list as list of strings of strategy names and scores.
    public List<String> getLeaderboardList() {
        sortLeaderboard();
        return leaderboardList;
    }
    public List<Pair<Integer, Strategy>> getStrategiesList() {
        return strategiesList;
    }

    public List<Strategy> getGeneticList() {
        sortLeaderboard();
        return geneticList;
    }

    //Adds element to array as a pair
    public void addElement(Strategy strat, int stratScore) {
        strategiesList.add(new Pair<>(stratScore, strat));
    }

    public void addRounds(int rounds) {
       this.rounds = rounds;
    }


    private void sortLeaderboard() {
        geneticList.clear();
        leaderboardList.clear();
        leaderboardList.add( "ROUNDS FOR GAME: " + rounds);
        //sorts the list of pairs by the key which is the score
        Collections.sort(strategiesList, Comparator.comparing(p -> -p.getKey()));

        //adds the displayable string version to leaderboard list interface variable.
        for (Pair<Integer, Strategy> element:strategiesList) {
            geneticList.add(element.getValue());
            leaderboardList.add(element.getValue().getName() + ": " + element.getKey());
        }
    }


}
