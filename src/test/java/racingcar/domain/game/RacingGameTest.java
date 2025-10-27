package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.CarName;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MoveStrategy;

class RacingGameTest {

    @Test
    @DisplayName("지정된 횟수만큼 라운드를 진행하고 결과를 반환한다")
    void playCollectsRoundResults() {
        Cars cars = Cars.fromNames(List.of(new CarName("pobi"), new CarName("woni")));
        MoveStrategy alwaysMove = () -> true;
        RacingGame racingGame = new RacingGame(cars, alwaysMove, 3);

        List<List<RacingGame.CarSnapshot>> results = racingGame.play();

        assertThat(results).hasSize(3);
        assertThat(results.get(2)).extracting(RacingGame.CarSnapshot::position)
                .containsExactly(3, 3);
    }

    @Test
    @DisplayName("우승자 이름을 반환한다")
    void winnerNames() {
        Cars cars = Cars.fromNames(List.of(new CarName("pobi"), new CarName("woni")));
        MoveStrategy strategy = new MoveStrategy() {
            private int counter;

            @Override
            public boolean canMove() {
                return counter++ % 2 == 0;
            }
        };
        RacingGame racingGame = new RacingGame(cars, strategy, 2);

        racingGame.play();

        List<String> winners = racingGame.winnerNames();
        assertThat(winners).containsExactly("pobi");
    }
}
