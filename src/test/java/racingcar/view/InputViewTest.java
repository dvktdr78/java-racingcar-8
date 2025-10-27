package racingcar.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.CarName;
import racingcar.domain.round.RaceRound;
import racingcar.validator.InputValidator;

class InputViewTest extends NsTest {
    private final InputView inputView = new InputView(new InputValidator());
    private static List<CarName> capturedNames;
    private static RaceRound capturedRound;

    @Test
    @DisplayName("자동차 이름과 시도 횟수를 순서대로 입력받는다")
    void readInputs() {
        assertSimpleTest(() -> {
            run("pobi,woni,jun", "5");

            assertThat(capturedNames).extracting(CarName::value)
                    .containsExactly("pobi", "woni", "jun");
            assertThat(capturedRound.value()).isEqualTo(5);
        });
    }

    @Test
    @DisplayName("잘못된 입력은 예외를 발생시킨다")
    void invalidInputThrowsException() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,,woni"))
                        .isInstanceOf(IllegalArgumentException.class)
        );

        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", "a"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        capturedNames = inputView.readCarNames();
        capturedRound = inputView.readRaceRound();
    }
}
