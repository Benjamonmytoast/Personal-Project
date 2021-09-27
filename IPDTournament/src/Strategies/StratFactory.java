package Strategies;

public class StratFactory {

    //Factory class used to create strategies for the GUI to decouple the strategies. Also easier to implement.
    public Strategy getStrategy(String stratType){
        if(stratType == null) {
            return null;
        }

        if(stratType.equalsIgnoreCase("TFT")){
            return new Tft();

        } else if(stratType.equalsIgnoreCase("RANDOM")) {
            return new Random();

        } else if(stratType.equalsIgnoreCase("PVALUE")) {
            return new PValue();

        } else if(stratType.equalsIgnoreCase("PAVLOV")) {
            return new Pavlov();

        } else if(stratType.equalsIgnoreCase("ALLD")) {
            return new AllD();

        } else if (stratType.equalsIgnoreCase("ALLC")) {
            return new AllC();

        } else if (stratType.equalsIgnoreCase("STATADAPT")) {
            return new StatisticalAdapt();

        } else if (stratType.equalsIgnoreCase("SCOREADAPT")) {
            return new ScoreAdapt();

        } else if (stratType.equalsIgnoreCase("PAWN")) {
            return new PawnCollude();

        } else if (stratType.equalsIgnoreCase("KING")) {
            return new KingCollude();

        }

        return null;
    }
}
