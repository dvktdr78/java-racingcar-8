package racingcar.domain.game;

import java.util.List;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;

public final class Winners {
    private final List<String> names;

    private Winners(List<String> names) {
        this.names = List.copyOf(names);
    }

    public static Winners from(Cars cars) {
        int maxPosition = cars.maxPosition();
        List<String> names = cars.cars().stream()
                .filter(car -> car.position() == maxPosition)
                .map(Car::name)
                .toList();
        return new Winners(names);
    }

    public List<String> names() {
        return names;
    }
}
