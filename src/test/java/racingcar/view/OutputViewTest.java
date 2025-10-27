package racingcar.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.game.RacingGame;

class OutputViewTest extends NsTest {
    private final OutputView outputView = new OutputView();

    @Test
    @DisplayName("라운드 결과와 우승자를 지정된 형식으로 출력한다")
    void printRoundAndWinners() {
        assertSimpleTest(() -> {
            run();
            assertThat(output()).contains(
                    UiText.Output.EXECUTION_RESULT_HEADER,
                    "pobi : --",
                    "woni : -",
                    UiText.Output.WINNER_PREFIX + "pobi, woni"
            );
        });
    }

    @Override
    public void runMain() {
        outputView.printResultHeader();
        outputView.printRound(List.of(
                new RacingGame.CarSnapshot("pobi", 2),
                new RacingGame.CarSnapshot("woni", 1)
        ));
        outputView.printWinners(List.of("pobi", "woni"));
    }
}
