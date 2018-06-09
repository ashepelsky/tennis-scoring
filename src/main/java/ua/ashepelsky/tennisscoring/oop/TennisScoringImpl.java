package ua.ashepelsky.tennisscoring.oop;

import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.Score;

import java.util.HashMap;
import java.util.Map;

public class TennisScoringImpl implements TennisScoring {

    private Map<Player, Score> gameScore;

    public TennisScoringImpl(Player player1, Player player2) {
        gameScore = new HashMap<>();

        gameScore.put(player1, Score.LOVE);
        gameScore.put(player2, Score.LOVE);

    }

    public void pointWonBy(Player player) {

    }

    public Map<Player, Score> getScore() {
        return null;
    }
}
