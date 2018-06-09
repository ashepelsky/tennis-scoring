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
            } else if (opponentScore.equals(this)) {
                return DEUCE;
            } else {
                return super.next(opponentScore);
            }
        }
    },
    DEUCE {
        @Override
        public Score next(Score opponentScore) {
            if (opponentScore.equals(DEUCE)) {
                return ADVANTAGE;
            } else if(opponentScore.equals(ADVANTAGE)) {
                return DEUCE;
            } else {
                throw new IllegalStateException("Invalid game state. Opponent score must be ADVANTAGE or DEUCE");
            }
        }
    },
    ADVANTAGE,
    WON;

    private static Score[] scores = Score.values();

    public Score next(Score opponentScore) {

        if (opponentScore.equals(WON) || this.equals(WON)) {
            throw new IllegalStateException("Set is already finished");
        }

        int nextOrdinal = this.ordinal() + 1;

        return scores[nextOrdinal % scores.length];
    }

}
