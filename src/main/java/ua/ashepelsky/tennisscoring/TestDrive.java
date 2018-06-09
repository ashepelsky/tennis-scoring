package ua.ashepelsky.tennisscoring;

import ua.ashepelsky.tennisscoring.core.Score;

public class TestDrive {

    public static void main(String[] args) {
        Score score = Score.LOVE;

        for (int i = 0; i < 4; i++) {
            System.out.println(score = score.next());
        }
    }
}
