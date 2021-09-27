package genetic_algorithms;

import Strategies.Response;
import Strategies.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Evolver {
    //strategy list must be divisible by 4.

    static double rateOfMutation = 0.05;

    public static Strategy[] breed(List<Strategy> individuals) {
        int splitNumber = 2;

        //splits the population in half
        int subSize = individuals.size()/splitNumber;

        //takes the first half to breed
        List<Strategy> firstHalf = individuals.subList(0, subSize);

        //resets individuals list
        individuals = new ArrayList<>();

        //for every pair of individuals (0,1), (2,3) etc.
        for (int parentCounter = 0; parentCounter < subSize; parentCounter = parentCounter+2) {

            //makes babies relative to how large the split was.
            for (int babyCounter = 0; babyCounter < (splitNumber*2); babyCounter++ ) {

                //adds a child of the parents to the new individual list.
                individuals.add(uniformCrossover(
                        (Candidate) firstHalf.get(parentCounter),
                        (Candidate) firstHalf.get(parentCounter+1)));
            }
        }


        //also converts list to array of strategy.
        return individuals.toArray(new Strategy[individuals.size()]);
    }

    //uniform crossover considers each bit seperately with a chance that it becomes one parent or the other.
    private static Strategy uniformCrossover(Candidate p1, Candidate p2){

        int depth = Dna.getDepth();
        Dna babyDna = new Dna();
        Candidate baby;

        //for each level of dna.
        for (int level = 0; level < depth; level++) {

            //for each chromosome in the level of dna;
            for (int actual = 0; actual < Math.pow(2, (2*level)); actual++) {

                java.util.Random random = new java.util.Random();

                //randomly select the baby dna from one of the parents.
                if (random.nextBoolean()) {
                    babyDna.setDna(level, actual, p1.getDna().getDna(level, actual));
                } else {
                    babyDna.setDna(level, actual, p2.getDna().getDna(level, actual));
                }


                //babyDna has a small chance of mutating
                if (random.nextDouble() < rateOfMutation) {
                    babyDna.setDna(level, actual, mutate(babyDna.getDna(level, actual)));
                }

            }

        }

        baby = new Candidate(babyDna);
        return baby;
    }

    //when provided with a response it returns the opposite.
    private static Response mutate(Response e) {
        return e==Response.D ? Response.C: Response.D;
    }
}
