package ua.ashepelsky.tennisscoring.functional;

import ua.ashepelsky.tennisscoring.core.Player;

public class FunctionalTennisScoring {

    public String score(GameSetState gameSetState) {

        return gameSetState.getFirstPlayer().getPlayer().getName() + ": " + gameSetState.getFirstPlayer().getScore().name()
            + " | "
            + gameSetState.getSecondPlayer().getPlayer().getName() + ": " + gameSetState.getSecondPlayer().getScore().name();
    }

    public GameSetState pointWonBy(GameSetState gameSetState, Player player) {

        if (player.equals(gameSetState.getFirstPlayer().getPlayer())) {
            gameSetState.getFirstPlayer().score();
        } else {
            gameSetState.getSecondPlayer().score();
        }

        return gameSetState;
    }

}
