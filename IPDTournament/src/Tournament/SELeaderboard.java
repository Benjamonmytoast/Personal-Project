package Tournament;

import Strategies.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SELeaderboard implements Leaderboard {

    private List<String> leaderboardList = new ArrayList<>();

    //reverse() used as the leaderboard is constructed from the worst performing to the best, but we want best to worst.
    public List<String> getLeaderboardList() {
        Collections.reverse(leaderboardList);
        return leaderboardList;
    }

    //Add a pair to the leaderBoard
    void addElement(String stratA, String stratB) {
        leaderboardList.add(stratA + " vs " + stratB);
    }

    //Add a single element
    void addElement(String strat) {
        leaderboardList.add("WINNER IS: " + strat);
    }
}
