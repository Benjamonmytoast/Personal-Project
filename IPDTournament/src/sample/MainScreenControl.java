package sample;

import Strategies.PMatrix;
import Strategies.StratFactory;
import Strategies.Strategy;
import Tournament.Leaderboard;
import Tournament.TourneyFactory;
import genetic_algorithms.NaturalSelector;
import genetic_algorithms.NaturalSelectorFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MainScreenControl {


    public ListView<String> strategyListView;
    public ListView<String> leaderboardListView;

    public Label errorLabel;
    public Label stratCount;
    public Label geneticLabel;
    public Label iterationLabel;

    public CheckBox geneticToggle;

    public LineChart<Number, Number> chart;

    public TextField iterationField;
    public TextField geneticPop;
    public TextField roundsField;

    public TextField rField;
    public TextField sField;
    public TextField tField;
    public TextField pField;

    public Button SElim;
    public Button RoundR;
    public Button guided;
    public Button natural;


    private int graph_counter = 1;
    private NaturalSelectorFactory nSFactory = new NaturalSelectorFactory();
    private StratFactory stratFactory = new StratFactory();
    private TourneyFactory tourneyFactory = new TourneyFactory();
    private List<Strategy> strategyList = new ArrayList<>();


    private void updateListView() {
        ObservableList<String> list = FXCollections.observableArrayList();

        for (Strategy strat: strategyList) {
            list.add(strat.getName());
        }

        strategyListView.setItems(list);
    }

    private void updateStratCount() {
        stratCount.setText("Strategies: " + strategyList.size());
    }

    private void updateStratAdded(String stratType) {
        strategyList.add(stratFactory.getStrategy(stratType));
        updateListView();
        updateStratCount();
    }

    public void handleRandom(ActionEvent actionEvent) {
        updateStratAdded("RANDOM");
    }

    public void handleScoreAdapt(ActionEvent actionEvent) {
        updateStratAdded("SCOREADAPT");
    }

    public void handlePval(ActionEvent actionEvent) {
        updateStratAdded("PVALUE");
    }

    public void handleAllC(ActionEvent actionEvent) {
        updateStratAdded("ALLC");
    }

    public void handleAllD(ActionEvent actionEvent) {
        updateStratAdded("ALLD");
    }

    public void handleTft(ActionEvent actionEvent) {
        updateStratAdded("TFT");
    }

    public void handlePavlov(ActionEvent actionEvent) {
        updateStratAdded("PAVLOV");
    }

    public void handleStatAdapt(ActionEvent actionEvent) {updateStratAdded("STATADAPT");}

    public void handlePawn(ActionEvent actionEvent) {updateStratAdded("PAWN");}

    public void handleKing(ActionEvent actionEvent) {updateStratAdded("KING");}

    public void handleRR(ActionEvent actionEvent) {
        doTourney("RR");
    }

    public void handleSElim(ActionEvent actionEvent) {
        //will only work if input is a power of 2 to ensure everyone plays the equal number of games and there is no BYE.
        if ((strategyList.size() & (strategyList.size() - 1)) != 0) {
            //this works using a binary and, as a number of base 2 in binary will be a single 1 with 0's,
            // one less than this will be the opposite, therefore the and will be 0
            // e.g 1000 & 0111
            errorLabel.setText("Must have 2^n strategies for Single Elim");
        } else {
            doTourney("SELIM");
        }
    }

    private void doTourney(String tourneyType) {

        Leaderboard leaderboard;
        int rounds;

        try {
            rounds = Integer.parseInt(roundsField.getText());
            if (strategyList.size() == 0) {
                errorLabel.setText("List cannot be empty");
            } else {

                leaderboard = tourneyFactory.getTourney(tourneyType)
                        .play(getStratArray(), rounds);

                ObservableList<String> list = FXCollections.observableArrayList();
                list.setAll(leaderboard.getLeaderboardList());
                leaderboardListView.setItems(list);
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("textfields must be integers.");
        }


    }

    //Guided evolution is where a set of individuals adapt relative to a user defined set of established algorithms.
    public void handleGuided(ActionEvent actionEvent) {
        doGeneticAlgorithm("Guided");
    }

    //Natural evolution is where a population of random individuals adapt relative to each other
    public void handleNatural(ActionEvent actionEvent) {
        doGeneticAlgorithm("Natural");
    }

    private void doGeneticAlgorithm(String type) {

        int population;
        int iterations;
        int rounds;

        try {
            rounds = Integer.parseInt(roundsField.getText());
            population = Integer.parseInt(geneticPop.getText());
            iterations = Integer.parseInt(iterationField.getText());


            if ((population%4) != 0) {
                //because top half of the population has to be paired.
                errorLabel.setText("population must be mod 4.");

            } else {
                NaturalSelector nsElector = nSFactory.getNaturalSelector(type, population,
                        iterations, rounds,
                        getStratArray());

                ObservableList<String> list = FXCollections.observableArrayList();
                list.setAll(nsElector.getLastLeaderboard().getLeaderboardList());
                leaderboardListView.setItems(list);

                updateGraph(nsElector.getBestList(), type + " best results");
            }

        } catch (NumberFormatException e) {
            errorLabel.setText("textfields must be integers.");
        }


    }

    private void updateGraph(List<XYChart.Data<Number, Number>> results, String name) {
        ObservableList<XYChart.Data<Number, Number>> bestList = FXCollections.observableArrayList();
        bestList.setAll(results);

        XYChart.Series<Number, Number> series = new XYChart.Series<>(bestList);
        series.setName(graph_counter + name);

        graph_counter++;
        chart.getData().add(series);

    }

    private Strategy[] getStratArray() {
        Strategy[] stratList = new Strategy[strategyList.size()];
        strategyList.toArray(stratList);
        return stratList;
    }

    public void handleClear(ActionEvent actionEvent) {
        strategyList.clear();
        updateListView();
        updateStratCount();
    }

    public void handleGenToggle(ActionEvent actionEvent) {
        genToggle(geneticToggle.isSelected());
    }

    private void genToggle(boolean isCheck) {
        geneticLabel.setVisible(isCheck);
        geneticPop.setVisible(isCheck);
        natural.setVisible(isCheck);
        guided.setVisible(isCheck);
        SElim.setVisible(!isCheck);
        RoundR.setVisible(!isCheck);
        iterationField.setVisible(isCheck);
        iterationLabel.setVisible(isCheck);
    }


    public void handleClearGraph(ActionEvent actionEvent) {
        graph_counter = 1;
        chart.getData().clear();
    }

    public void handleUpdatePayoff(ActionEvent actionEvent) {
        int t = Integer.parseInt(tField.getText());
        int r = Integer.parseInt(rField.getText());
        int p = Integer.parseInt(pField.getText());
        int s = Integer.parseInt(sField.getText());

        PMatrix.getInstance().updateTRPS(t,r,p,s);
    }
}
