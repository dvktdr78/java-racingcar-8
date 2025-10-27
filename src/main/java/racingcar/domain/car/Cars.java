package racingcar.domain.car;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.strategy.MoveStrategy;

public final class Cars {
    private final List<Car> cars;
    public Cars(List<Car> cars) {
        this.cars = List.copyOf(cars);
    }

    public static Cars fromNames(List<CarName> carNames) {
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toUnmodifiableList());
        return new Cars(cars);
    }

    public void moveAll(MoveStrategy strategy) {
        cars.forEach(car -> car.move(strategy));
    }

    public int maxPosition() {
        return cars.stream()
                .mapToInt(Car::position)
                .max()
                .orElse(0);
    }

    public List<Car> cars() {
        return Collections.unmodifiableList(cars);
    }
}
