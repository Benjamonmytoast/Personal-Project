package Tournament;

import Strategies.Strategy;

public interface Tournament {
     int roundsLower = 50;
     int roundsUpper = 300;
    Leaderboard play(Strategy[] strategies, int rounds);
}
