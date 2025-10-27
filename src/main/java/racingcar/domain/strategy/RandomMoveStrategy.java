package racingcar.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy {
    private static final int MIN = 0;
    private static final int MAX = 9;
    private static final int MIN_MOVE_VALUE = 4;

    @Override
    public boolean canMove() {
        int randomNumber = Randoms.pickNumberInRange(MIN, MAX);
        return randomNumber >= MIN_MOVE_VALUE;
    }
}
