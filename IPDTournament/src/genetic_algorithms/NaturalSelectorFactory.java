package genetic_algorithms;

import Strategies.Strategy;

public class NaturalSelectorFactory {
    public NaturalSelector getNaturalSelector(String type, int population, int iterations, int rounds, Strategy[] strategies){



        if(type == null) {
            return null;
        }

        if(type.equalsIgnoreCase("NATURAL")){
            return new Natural(population, iterations, rounds);

        } else if(type.equalsIgnoreCase("GUIDED")) {
            return new Guided(population, iterations, rounds, strategies);

        }

        return null;
    }
}
