package Tournament;

import Strategies.Tft;
import Tournament.RRLeaderboard;
import org.junit.Test;

import static org.junit.Assert.*;

public class RRLeaderboardTest {

    @Test
    public void testRRLeaderBoard() {
        RRLeaderboard board = new RRLeaderboard();

        board.addElement(new Tft(), 100);
        board.addElement(new Tft(), 99);
        board.addElement(new Tft(), 102);
        board.addElement(new Tft(), 150);
        board.addElement(new Tft(), 60);
        board.addElement(new Tft(), 40);

        System.out.print(board.getLeaderboardList());

        assertTrue(true);
    }

}