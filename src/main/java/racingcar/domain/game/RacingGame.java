package racingcar.domain.game;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MoveStrategy;

public final class RacingGame {
    private final Cars cars;
    private final MoveStrategy moveStrategy;
    private final int attemptCount;

    public RacingGame(Cars cars, MoveStrategy moveStrategy, int attemptCount) {
        this.cars = cars;
        this.moveStrategy = moveStrategy;
        this.attemptCount = attemptCount;
    }

    public List<List<CarSnapshot>> play() {
        List<List<CarSnapshot>> snapshots = new ArrayList<>();
        for (int i = 0; i < attemptCount; i++) {
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
