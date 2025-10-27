package racingcar.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.CarName;
import racingcar.domain.game.RacingGame;
import racingcar.domain.round.RaceRound;
import racingcar.validator.InputValidator;

class RacingConsoleTest extends NsTest {
    private final InputView inputView = new InputView(new InputValidator());
    private final OutputView outputView = new OutputView();
    private final RacingConsole console = new RacingConsole(inputView, outputView);

    private static List<CarName> capturedNames;
    private static RaceRound capturedRound;

    @Test
    @DisplayName("입출력 파사드가 흐름을 위임한다")
    void delegateToViews() {
        assertSimpleTest(() -> {
            run("pobi,woni", "3");

            assertThat(capturedNames).extracting(CarName::value)
                    .containsExactly("pobi", "woni");
            assertThat(capturedRound.value()).isEqualTo(3);
            assertThat(output())
                    .contains(UiText.Prompt.CAR_NAMES)
                    .contains(UiText.Prompt.ATTEMPT_COUNT)
                    .contains(UiText.Output.EXECUTION_RESULT_HEADER)
                    .contains("pobi : --")
                    .contains("최종 우승자 : pobi, woni");
        });
    }

    @Override
    public void runMain() {
        capturedNames = console.readCarNames();
        capturedRound = console.readRaceRound();
        console.printResultHeader();
        console.printRound(List.of(
                new RacingGame.CarSnapshot("pobi", 2),
                new RacingGame.CarSnapshot("woni", 1)
        ));
        console.printWinners(List.of("pobi", "woni"));
    }
}
