package ua.ashepelsky.tennisscoring.oop;

import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.PlayerGameSession;
import ua.ashepelsky.tennisscoring.core.Score;

import java.util.HashMap;
import java.util.Map;

public class TennisScoringImpl implements TennisScoring {

    private Map<Player, Score> gameScore;

    private PlayerGameSession firstPlayer;
    private PlayerGameSession secondPlayer;

    public TennisScoringImpl(Player player1, Player player2) {
        gameScore = new HashMap<>();

        firstPlayer = new PlayerGameSession(player1);
        secondPlayer = new PlayerGameSession(player2);

        firstPlayer.setOpponentSession(secondPlayer);
    }

    public void pointWonBy(Player player) {

        if(player.equals(firstPlayer.getPlayer())) {
            firstPlayer.score();
        } else {
            secondPlayer.score();
        }

    }

    public Map<Player, Score> getScore() {
        gameScore.put(firstPlayer.getPlayer(), firstPlayer.getScore());
        gameScore.put(secondPlayer.getPlayer(), secondPlayer.getScore());
        return gameScore;
    }

}
