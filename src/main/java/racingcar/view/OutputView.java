package racingcar.view;

import java.util.List;
import racingcar.domain.game.RacingGame;

public final class OutputView {

    public void printResultHeader() {
        System.out.println();
        System.out.println(UiText.Output.EXECUTION_RESULT_HEADER);
    }

    public void printRound(List<RacingGame.CarSnapshot> snapshots) {
        snapshots.forEach(snapshot -> System.out.println(snapshot.name() + " : "
                + UiText.Output.POSITION_MARK.repeat(snapshot.position())));
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println(UiText.Output.WINNER_PREFIX + String.join(", ", winners));
    }

    public void printError(String message) {
        System.out.println(UiText.Error.PREFIX + message);
    }
}
