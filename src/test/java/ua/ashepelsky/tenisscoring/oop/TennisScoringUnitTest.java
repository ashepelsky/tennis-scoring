package ua.ashepelsky.tenisscoring.oop;

import org.junit.Before;
import org.junit.Test;
import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.Score;
import ua.ashepelsky.tennisscoring.oop.TennisScoring;
import ua.ashepelsky.tennisscoring.oop.TennisScoringImpl;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TennisScoringUnitTest {

    private final Player PLAYER_1 = new Player("1");
    private final Player PLAYER_2 = new Player("2");
    private TennisScoring tennisScoring;

    @Before
    public void before() {
        tennisScoring = new TennisScoringImpl(PLAYER_1, PLAYER_2);
    }

    @Test
    public void verifyScore() {

        Map<Player, Score> result = tennisScoring.getScore();

        assertNotNull("Game Score can not be null", result);
        assertNotNull("Score for player one should not be null", result.get(PLAYER_1));
        assertNotNull("Score for player two should not be null", result.get(PLAYER_2));
        assertNotNull("Initial score should be \'LOVE\'", result.get(PLAYER_1));
        assertNotNull("Initial score should be \'LOVE\'", result.get(PLAYER_2));
    }

    @Test
    public void playerOneWinsAfterFourPoints() {
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);

        Map<Player, Score> result = tennisScoring.getScore();

        assertEquals("A game should be won by the first player to have won at least four points in total" +
            " and at least two points more than the opponent", Score.WON, result.get(PLAYER_1));
    }

    @Test
    public void playersGetDeuce() {
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);

        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);

        Map<Player, Score> result = tennisScoring.getScore();

        assertEquals("If at least three points have been scored by each player, and the scores are equal" +
            " the score should be 'DEUCE'",
            Score.DEUCE, result.get(PLAYER_1));

        assertEquals("If at least three points have been scored by each player, and the scores are equal" +
            " the score should be 'DEUCE'",
            Score.DEUCE, result.get(PLAYER_2));
    }

    @Test
    public void playerOneGetAdvantageAfterScoringInDeuce() {
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);

        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);

        tennisScoring.pointWonBy(PLAYER_1);

        Map<Player, Score> result = tennisScoring.getScore();

        assertEquals("If a player scores and the game is in a deuce state, the scoring player gets 'ADVANTAGE'",
            Score.ADVANTAGE, result.get(PLAYER_1));
    }

    @Test
    public void playerTwoGetAdvantageAfterScoringInDeuce() {
        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);

        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);

        tennisScoring.pointWonBy(PLAYER_2);

        Map<Player, Score> result = tennisScoring.getScore();

        assertEquals("If a player scores and the game is in a deuce state, the scoring player gets 'ADVANTAGE'",
            Score.ADVANTAGE, result.get(PLAYER_2));
    }

    @Test
    public void playerOneLoseAdvantageAfterOpponentScoring() {
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);

        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);

        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_2);

        Map<Player, Score> result = tennisScoring.getScore();

        assertEquals("If a player scores and his opponent has 'advantage' then the game is tied in a 'DEUCE' state",
            Score.DEUCE, result.get(PLAYER_1));
    }

    @Test
    public void playerTwoWinsAfterScoringInAdvantage() {
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);
        tennisScoring.pointWonBy(PLAYER_1);

        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);

        tennisScoring.pointWonBy(PLAYER_2);
        tennisScoring.pointWonBy(PLAYER_2);

        Map<Player, Score> result = tennisScoring.getScore();

        assertEquals("If a player scores and has 'ADVANTAGE' then the scoring player has 'WON'",
            Score.WON, result.get(PLAYER_2));
    }

}
