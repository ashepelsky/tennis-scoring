package ua.ashepelsky.tennisscoring.core;

public enum Score {
    LOVE,
    FIFTEEN,
    THIRTY,
    FORTY {

        @Override
        public Score next(Score opponentScore) {

            if (opponentScore.ordinal() < this.ordinal()) {
                return WON;
            } else {
                throw new IllegalStateException("Invalid game state. Game should be in DEUCE state");
            }
        }
    },
    DEUCE {

        @Override
        public Score next(Score opponentScore) {

            if (opponentScore.equals(DEUCE)) {
                return ADVANTAGE;
            } else if (opponentScore.equals(ADVANTAGE)) {
                return DEUCE;
            } else {
                throw new IllegalStateException("Invalid game state. Opponent score must be ADVANTAGE or DEUCE");
            }
        }
    },
    ADVANTAGE,
    WON {

        @Override
        public Score next(Score opponentScore) {

            throw new IllegalStateException("Set is already finished");
        }
    };


    private static Score[] scores = Score.values();

    public Score next(Score opponentScore) {

        return scores[this.ordinal() + 1];
    }

}
