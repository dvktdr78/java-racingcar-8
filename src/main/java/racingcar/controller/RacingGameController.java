package racingcar.controller;

import java.util.List;
import racingcar.domain.car.CarName;
import racingcar.domain.car.Cars;
import racingcar.domain.game.RacingGame;
import racingcar.domain.round.RaceRound;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.view.RacingConsole;

public final class RacingGameController {
    private final RacingConsole console;
    private final MoveStrategy moveStrategy;

    public RacingGameController(RacingConsole console, MoveStrategy moveStrategy) {
        this.console = console;
        this.moveStrategy = moveStrategy;
    }

    public void run() {
        try {
            List<CarName> carNames = console.readCarNames();
            RaceRound raceRound = console.readRaceRound();

            Cars cars = Cars.fromNames(carNames);
            RacingGame racingGame = new RacingGame(cars, moveStrategy, raceRound);

            console.printResultHeader();
            List<List<RacingGame.CarSnapshot>> snapshots = racingGame.play();
            snapshots.forEach(console::printRound);
            console.printWinners(racingGame.winnerNames());
        } catch (IllegalArgumentException exception) {
            console.printError(exception.getMessage());
            throw exception;
        }
    }
}
