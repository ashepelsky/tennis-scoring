package ua.ashepelsky.tennisscoring.oop;

import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.Score;

import java.util.HashMap;
import java.util.Map;

public class TennisScoringImpl implements TennisScoring {

    private Map<Player, Score> gameScore;
    private Player p1;
    private Player p2;
    private Score s1;
    private Score s2;

    public TennisScoringImpl(Player player1, Player player2) {
        gameScore = new HashMap<>();

        gameScore.put(p1 = player1, s1 = Score.LOVE);
        gameScore.put(p2 = player2, s2 = Score.LOVE);

    }

    public void pointWonBy(Player player) {

        if(player.equals(p1)) {

            if (s2.equals(Score.ADVANTAGE)) {
                s2 = Score.DEUCE;
            }
            s1 = s1.next(s2);


        } else {

            if (s1.equals(Score.ADVANTAGE)) {
                s1 = Score.DEUCE;
            }

            s2 = s2.next(s1);
        }

        if (s1.equals(Score.FORTY) && s2.equals(Score.FORTY)) {
            s2 = Score.DEUCE;
            s1 = Score.DEUCE;
        }

    }

    public Map<Player, Score> getScore() {
        gameScore.put(p1, s1);
        gameScore.put(p2, s2);
        return gameScore;
    }

}
