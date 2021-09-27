package genetic_algorithms;


import Strategies.Strategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import static org.junit.Assert.*;

public class Experiments {
    private NaturalSelectorFactory nSFactory = new NaturalSelectorFactory();

    @Test
    public void testNatural() {/*
        Strategy[] stratArray = new Strategy[5];
        String type = "Natural";

        int population;
        int iterations;
        int rounds;

        rounds =;
        population =;
        iterations =;


        NaturalSelector nsElector = nSFactory.getNaturalSelector(type, population,
                iterations, rounds,
                stratArray);

        ObservableList<String> list = FXCollections.observableArrayList();
        list.setAll(nsElector.getLastLeaderboard().getLeaderboardList());
        leaderboardListView.setItems(list);

        updateGraph(nsElector.getBestList(), type + " best results");*/
    }
}
