package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.CarName;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MoveStrategy;

class WinnersTest {

    @Test
    @DisplayName("우승자 목록을 입력 순서대로 반환한다")
    void returnWinnersInInputOrder() {
        Cars cars = Cars.fromNames(List.of(
                new CarName("pobi"),
                new CarName("woni"),
                new CarName("jun")
        ));

        MoveStrategy firstOnly = new MoveStrategy() {
            private final Iterator<Boolean> iterator = List.of(true, false, false).iterator();
            @Override
            public boolean canMove() {
                return iterator.hasNext() && iterator.next();
            }
        };
        MoveStrategy secondOnly = new MoveStrategy() {
            private final Iterator<Boolean> iterator = List.of(false, true, false).iterator();
            @Override
            public boolean canMove() {
                return iterator.hasNext() && iterator.next();
            }
        };

        cars.moveAll(() -> true);     // 모두 1칸
        cars.moveAll(firstOnly);      // 첫 번째만 이동 -> pobi 2, woni 1
        cars.moveAll(secondOnly);     // 두 번째만 이동 -> pobi 2, woni 2

        Winners winners = Winners.from(cars);

        assertThat(winners.names()).containsExactly("pobi", "woni");
    }
}
