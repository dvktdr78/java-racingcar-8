package racingcar.domain.round;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaceRoundTest {

    @Test
    @DisplayName("시도 횟수가 1 이상이면 생성된다")
    void createRaceRound() {
        RaceRound round = RaceRound.of(3);

        assertThat(round.value()).isEqualTo(3);
    }

    @Test
    @DisplayName("시도 횟수가 1 미만이면 예외")
    void throwWhenLessThanOne() {
        assertThatThrownBy(() -> RaceRound.of(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
