package racingcar.domain.round;

import racingcar.view.UiText;

public final class RaceRound {
    private static final int MIN_ATTEMPT_COUNT = 1;

    private final int value;

    private RaceRound(int value) {
        if (value < MIN_ATTEMPT_COUNT) {
            throw new IllegalArgumentException(UiText.Error.ATTEMPT_TOO_SMALL);
        }
        this.value = value;
    }

    public static RaceRound of(int value) {
        return new RaceRound(value);
    }

    public int value() {
        return value;
    }
}
