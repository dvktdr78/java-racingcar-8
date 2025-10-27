package racingcar.domain.car;

import racingcar.domain.strategy.MoveStrategy;

public final class Car {
    private final CarName name;
    private int position;

    public Car(CarName name) {
        this.name = name;
        this.position = 0;
    }

    public String name() {
        return name.value();
    }

    public int position() {
        return position;
    }

    public void move(MoveStrategy strategy) {
        if (strategy.canMove()) {
            position++;
        }
    }
}
