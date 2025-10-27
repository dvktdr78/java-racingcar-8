package racingcar.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.view.UiText;

class InputValidatorTest {
    private final InputValidator validator = new InputValidator();

    @Test
    @DisplayName("쉼표로 구분된 자동차 이름을 공백 제거 후 반환한다")
    void validateCarNames() {
        List<String> names = validator.validateCarNames(" pobi , woni , jun ");

        assertThat(names).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("자동차 이름 입력이 비어 있으면 예외")
    void validateCarNamesBlank() {
        assertThatThrownBy(() -> validator.validateCarNames("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.EMPTY_INPUT);
    }

    @Test
    @DisplayName("자동차 이름 중 비어 있는 항목이 있으면 예외")
    void validateCarNamesContainsBlank() {
        assertThatThrownBy(() -> validator.validateCarNames("pobi, ,woni"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.NAME_EMPTY);
    }

    @Test
    @DisplayName("시도 횟수는 숫자 문자열이어야 한다")
    void validateAttemptCountNumeric() {
        int count = validator.validateAttemptCount(" 10 ");

        assertThat(count).isEqualTo(10);
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아니면 예외")
    void validateAttemptCountNotNumeric() {
        assertThatThrownBy(() -> validator.validateAttemptCount("1a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.NOT_NUMERIC);
    }

    @Test
    @DisplayName("시도 횟수가 1 미만이면 예외")
    void validateAttemptCountTooSmall() {
        assertThatThrownBy(() -> validator.validateAttemptCount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.ATTEMPT_TOO_SMALL);
    }
}
