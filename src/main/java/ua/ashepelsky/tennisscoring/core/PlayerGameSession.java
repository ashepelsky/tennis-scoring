package ua.ashepelsky.tennisscoring.core;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PlayerGameSession {

    private Player player;
    @Setter
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

    public void setOpponentSession(PlayerGameSession opponentSession) {

        this.opponentSession = opponentSession;

        if (opponentSession.getOpponentSession() == null) {
            opponentSession.setOpponentSession(this);
        }
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
