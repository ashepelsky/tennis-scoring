package ua.ashepelsky.tenisscoring.functonal;

import org.junit.Before;
import org.junit.Test;
import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.Score;
import ua.ashepelsky.tennisscoring.functional.FunctionalTennisScoring;
import ua.ashepelsky.tennisscoring.functional.GameSetState;

import static org.junit.Assert.assertEquals;
import static ua.ashepelsky.tennisscoring.core.Score.DEUCE;
import static ua.ashepelsky.tennisscoring.core.Score.FIFTEEN;
import static ua.ashepelsky.tennisscoring.core.Score.LOVE;
import static ua.ashepelsky.tennisscoring.core.Score.WON;

public class FunctionalTennisScoringUnitTest {

    private final String FIRST_PLAYER_NAME = "Player1";
    private final String SECOND_PLAYER_NAME = "Player2";

    private final Player PLAYER_1 = new Player(FIRST_PLAYER_NAME);
    private final Player PLAYER_2 = new Player(SECOND_PLAYER_NAME);

    private FunctionalTennisScoring tennisScoring = new FunctionalTennisScoring();
    private GameSetState gameSetState;

    @Before
    public void before() {
        gameSetState = new GameSetState(PLAYER_1, PLAYER_2);
    }

    @Test
    public void getInitialScore() {

        String result = tennisScoring.score(gameSetState);

        assertEquals("String score pattern should much provided one",
            expectedScoreString(LOVE, LOVE), result);
    }

    @Test
    public void playerOneScores() {

        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        assertEquals("One point for first player should be scored",
            FIFTEEN, gameSetState.getFirstPlayer().getScore());
    }

    @Test
    public void playerTwoScores() {

        tennisScoring.pointWonBy(gameSetState, PLAYER_2);

        assertEquals("One point for second player should be scored",
            FIFTEEN, gameSetState.getSecondPlayer().getScore());
    }

    @Test
    public void playerOneWinsAfterFourPoints_checkPlayerScore() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        Score result = gameSetState.getFirstPlayer().getScore();

        assertEquals("A game should be won by the first player to have won at least four points in total" +
                " and at least two points more than the opponent", WON, result);
    }

    @Test
    public void playerOneWinsAfterFourPoints_checkScoreString() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        String result = tennisScoring.score(gameSetState);

        assertEquals("A game should be won by the first player and score string should match provided one"
            , expectedScoreString(WON, LOVE), result);
    }

    @Test
    public void playersGotDeuce_checkPlayerScore() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);

        Score playerOneResult = gameSetState.getFirstPlayer().getScore();
        Score playerTwoResult = gameSetState.getSecondPlayer().getScore();

        assertEquals("If at least three points have been scored by each player, and the scores are equal" +
            " the score should be 'DEUCE'", DEUCE, playerOneResult);

        assertEquals("If at least three points have been scored by each player, and the scores are equal" +
            " the score should be 'DEUCE'", DEUCE, playerTwoResult);
    }

    @Test
    public void playersGotDeuce_checkScoreString() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);

        String result = tennisScoring.score(gameSetState);

        assertEquals("Score should be 'DEUCE' and score string should match provided one",
            expectedScoreString(DEUCE, DEUCE), result);
    }

    @Test
    public void playerOneGotAdvantageAfterScoringInDeuce() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);

        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        Score result = gameSetState.getFirstPlayer().getScore();

        assertEquals("If a player scores and the game is in a deuce state, the scoring player should get 'ADVANTAGE'",
            Score.ADVANTAGE, result);
    }

    @Test
    public void playerOneLosesAdvantageAfterOpponentScoring() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);

        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);


        Score result = gameSetState.getFirstPlayer().getScore();

        assertEquals("If a player scores and his opponent has 'advantage' then the game should be tied in a" +
                " 'DEUCE' state",
            Score.DEUCE, result);
    }

    @Test
    public void playerTwoWinsAfterScoringInAdvantage() {
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);

        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);
        tennisScoring.pointWonBy(gameSetState, PLAYER_1);

        tennisScoring.pointWonBy(gameSetState, PLAYER_2);
        tennisScoring.pointWonBy(gameSetState, PLAYER_2);


        Score result = gameSetState.getFirstPlayer().getScore();

        assertEquals("If a player scores and has 'ADVANTAGE' then the scoring player has 'WON'",
            Score.WON, result);
    }

    private String expectedScoreString(Score one, Score two) {
        return FIRST_PLAYER_NAME + ": " + one.name() + " | "
            + SECOND_PLAYER_NAME +": " + two.name();
    }
}
