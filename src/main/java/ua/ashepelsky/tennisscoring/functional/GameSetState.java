package ua.ashepelsky.tennisscoring.functional;

import lombok.Data;
import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.PlayerGameSession;

@Data
public class GameSetState {

    private PlayerGameSession firstPlayer;
    private PlayerGameSession secondPlayer;

    public GameSetState(Player player1, Player player2) {
        firstPlayer = new PlayerGameSession(player1);
        secondPlayer = new PlayerGameSession(player2);

        firstPlayer.setOpponentSession(secondPlayer);
        secondPlayer.setOpponentSession(firstPlayer);
    }
}
