package ua.ashepelsky.tennisscoring.oop;

import ua.ashepelsky.tennisscoring.core.Player;
import ua.ashepelsky.tennisscoring.core.Score;

import java.util.Map;

public interface TennisScoring {

    void pointWonBy(Player player);

    Map<Player, Score> getScore();

}
