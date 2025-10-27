package racingcar.domain.game;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.car.Cars;
import racingcar.domain.round.RaceRound;
import racingcar.domain.strategy.MoveStrategy;

public final class RacingGame {
    private final Cars cars;
    private final MoveStrategy moveStrategy;
    private final RaceRound raceRound;

    public RacingGame(Cars cars, MoveStrategy moveStrategy, RaceRound raceRound) {
        this.cars = cars;
        this.moveStrategy = moveStrategy;
        this.raceRound = raceRound;
    }

    public List<List<CarSnapshot>> play() {
        List<List<CarSnapshot>> snapshots = new ArrayList<>();
        for (int i = 0; i < raceRound.value(); i++) {
            cars.moveAll(moveStrategy);
            snapshots.add(carsSnapshot());
        }
        return snapshots;
    }

    public List<String> winnerNames() {
        return Winners.from(cars).names();
    }

    private List<CarSnapshot> carsSnapshot() {
        return cars.cars().stream()
                .map(car -> new CarSnapshot(car.name(), car.position()))
                .toList();
    }

    public record CarSnapshot(String name, int position) {}
}
