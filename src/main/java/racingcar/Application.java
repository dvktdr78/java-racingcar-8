package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.domain.strategy.RandomMoveStrategy;
import racingcar.validator.InputValidator;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.view.RacingConsole;

public final class Application {
    public static void main(String[] args) {
        InputValidator validator = new InputValidator();
        InputView inputView = new InputView(validator);
        OutputView outputView = new OutputView();
        RacingConsole console = new RacingConsole(inputView, outputView);
        MoveStrategy moveStrategy = new RandomMoveStrategy();
        RacingGameController controller = new RacingGameController(console, moveStrategy);
        controller.run();
    }
}
