package racingcar.domain.car;

public final class Car {
    private final CarName name;
    private int position;

    public Car(CarName name) {
        this.name = name;
        this.position = 0;
    }

    public CarName getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}