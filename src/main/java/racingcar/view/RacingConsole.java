package racingcar.view;

import java.util.List;
import racingcar.domain.car.CarName;
import racingcar.domain.game.RacingGame;
import racingcar.domain.round.RaceRound;

public final class RacingConsole {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingConsole(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<CarName> readCarNames() {
        outputView.printCarNamesPrompt();
        return inputView.readCarNames();
    }

    public RaceRound readRaceRound() {
        outputView.printRaceRoundPrompt();
        return inputView.readRaceRound();
    }

    public void printResultHeader() {
        outputView.printResultHeader();
    }

    public void printRound(List<RacingGame.CarSnapshot> snapshots) {
        outputView.printRound(snapshots);
    }

    public void printWinners(List<String> winners) {
        outputView.printWinners(winners);
    }

    public void printError(String message) {
        outputView.printError(message);
    }
}
