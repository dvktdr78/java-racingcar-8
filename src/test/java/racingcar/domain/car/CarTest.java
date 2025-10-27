package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.strategy.MoveStrategy;

class CarTest {

    @Test
    @DisplayName("자동차는 생성 시 위치가 0이다")
    void carInitialPositionIsZero() {
        Car car = new Car(new CarName("pobi"));

        assertThat(car.position()).isZero();
    }

    @Test
    @DisplayName("이동 전략이 true를 반환하면 한 칸 전진한다")
    void carMovesWhenStrategyAllows() {
        Car car = new Car(new CarName("pobi"));
        MoveStrategy moveAlways = () -> true;

        car.move(moveAlways);

        assertThat(car.position()).isOne();
    }

    @Test
    @DisplayName("이동 전략이 false를 반환하면 제자리다")
    void carDoesNotMoveWhenStrategyBlocks() {
        Car car = new Car(new CarName("pobi"));
        MoveStrategy stayStill = () -> false;

        car.move(stayStill);

        assertThat(car.position()).isZero();
    }
}
