package racingcar.domain.car;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.domain.strategy.MoveStrategy;

class CarsTest {

    @Test
    @DisplayName("모든 자동차가 이동 전략에 따라 이동한다")
    void moveAll() {
        Cars cars = Cars.fromNames(List.of(new CarName("pobi"), new CarName("woni")));
        Iterator<Boolean> iterator = Arrays.asList(true, false).iterator();
        MoveStrategy alternatingStrategy = iterator::next;

        cars.moveAll(alternatingStrategy);

        List<Car> actual = cars.cars();
        assertThat(actual.get(0).position()).isEqualTo(1);
        assertThat(actual.get(1).position()).isEqualTo(0);
    }

    @Test
    @DisplayName("최대 위치를 계산한다")
    void maxPosition() {
        Cars cars = Cars.fromNames(List.of(
                new CarName("pobi"),
                new CarName("woni"),
                new CarName("jun"))
        );

        MoveStrategy alwaysMove = () -> true;

        cars.moveAll(alwaysMove);   // 모두 1칸
        cars.moveAll(new MoveStrategy() { // 첫 번째만 이동
            private final Iterator<Boolean> iterator = Arrays.asList(true, false, false).iterator();
            @Override
            public boolean canMove() {
                return iterator.hasNext() && iterator.next();
            }
        });
        cars.moveAll(new MoveStrategy() { // 두 번째만 이동
            private final Iterator<Boolean> iterator = Arrays.asList(false, true, false).iterator();
            @Override
            public boolean canMove() {
                return iterator.hasNext() && iterator.next();
            }
        });

        assertThat(cars.maxPosition()).isEqualTo(2);
    }

}
