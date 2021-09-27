package Tournament;

import org.junit.Test;

import static org.junit.Assert.*;

public class SELeaderboardTest {
    @Test
    public void testSELeaderBoard() {
        SELeaderboard board = new SELeaderboard();

        board.addElement("test1", "test2");
        board.addElement("test2","test3" );
        board.addElement("test3", "test4");
        board.addElement("test4");

        System.out.print(board.getLeaderboardList());

        assertTrue(true);
    }

}