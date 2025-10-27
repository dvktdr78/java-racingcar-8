package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import racingcar.domain.car.CarName;
import racingcar.domain.round.RaceRound;
import racingcar.validator.InputValidator;

public final class InputView {
    private final InputValidator validator;

    public InputView(InputValidator validator) {
        this.validator = validator;
    }

    public List<CarName> readCarNames() {
        printMessage(UiText.Prompt.CAR_NAMES);
        String input = Console.readLine();
        List<String> names = validator.validateCarNames(input);
        return names.stream()
                .map(CarName::new)
                .toList();
    }

    public RaceRound readRaceRound() {
        printMessage(UiText.Prompt.ATTEMPT_COUNT);
        String input = Console.readLine();
        int count = validator.validateAttemptCount(input);
        return RaceRound.of(count);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}
