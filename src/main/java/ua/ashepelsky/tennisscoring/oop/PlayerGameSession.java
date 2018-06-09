package ua.ashepelsky.tennisscoring.oop;

import lombok.Data;
import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.Score;

@Data
public class PlayerGameSession {

    private Player player;
    private Score score;
    private PlayerGameSession opponentSession;

    public PlayerGameSession(Player player) {
        this.player = player;
        this.score = Score.LOVE;
    }

    public void score() {

        if (opponentSession == null)
            throw new IllegalStateException("Set Opponent Game Session");

        score = score.next(opponentSession.getScore());

        if (isDeuce() || isOpponentAdvantage())
            this.deuce();
    }

    private boolean isDeuce() {

        return score.equals(Score.FORTY) && opponentSession.getScore().equals(Score.FORTY);
    }

    private boolean isOpponentAdvantage() {

        return opponentSession.getScore().equals(Score.ADVANTAGE);
    }

    private void deuce() {

        score = Score.DEUCE;
        opponentSession.setScore(Score.DEUCE);
    }
}
