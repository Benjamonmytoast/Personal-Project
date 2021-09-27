package Tournament;

public class TourneyFactory {

    public Tournament getTourney(String tourneyType){
        if(tourneyType == null) {
            return null;
        }

        if(tourneyType.equalsIgnoreCase("RR")){
            return new Round_Robin();

        } else if(tourneyType.equalsIgnoreCase("SELIM")) {
            return new Single_Elimination();

        }

        return null;
    }
}
