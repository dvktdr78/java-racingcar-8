package racingcar.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.view.RacingConsole;
import racingcar.view.UiText;

class RacingGameControllerTest extends NsTest {
    private static RacingGameController controller;

    @Test
    @DisplayName("전체 경주 흐름을 실행한다")
    void runGame() {
        assertSimpleTest(() -> {
            run("pobi,woni", "3");

            assertThat(output())
                    .contains(UiText.Prompt.CAR_NAMES)
                    .contains(UiText.Prompt.ATTEMPT_COUNT)
                    .contains(UiText.Output.EXECUTION_RESULT_HEADER)
                    .contains("pobi : ---")
                    .contains("woni : ---")
                    .contains(UiText.Output.WINNER_PREFIX + "pobi, woni");
        });
    }

    @Test
    @DisplayName("잘못된 입력이 들어오면 에러 메시지를 출력하고 예외를 던진다")
    void invalidInputPrintsError() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("pobi,,woni"))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains(UiText.Error.PREFIX);
        });
    }

    @Override
    public void runMain() {
        InputView inputView = new InputView(new InputValidator());
        OutputView outputView = new OutputView();
        RacingConsole console = new RacingConsole(inputView, outputView);
        MoveStrategy alwaysMove = () -> true;
        controller = new RacingGameController(console, alwaysMove);
        controller.run();
    }
}
