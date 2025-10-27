package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.view.UiText;

class CarNameTest {

    @Test
    @DisplayName("이름이 null이면 예외를 던진다")
    void throwExceptionWhenNameNull() {
        assertThatThrownBy(() -> new CarName(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.NAME_EMPTY);
    }

    @Test
    @DisplayName("이름이 비어 있거나 공백뿐이면 예외를 던진다")
    void throwExceptionWhenNameBlank() {
        assertThatThrownBy(() -> new CarName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.NAME_EMPTY);
        assertThatThrownBy(() -> new CarName("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.NAME_EMPTY);
    }

    @Test
    @DisplayName("이름 길이가 5자를 초과하면 예외를 던진다")
    void throwExceptionWhenNameTooLong() {
        assertThatThrownBy(() -> new CarName("abcdef"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(UiText.Error.NAME_UNVALID_LENGTH);
    }

    @Test
    @DisplayName("앞뒤 공백은 제거된 상태로 저장된다")
    void trimName() {
        CarName carName = new CarName("  pobi  ");

        assertThat(carName.value()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("1~5자의 이름은 생성된다")
    void createValidName() {
        assertThat(new CarName("a").value()).isEqualTo("a");
        assertThat(new CarName("pobi").value()).isEqualTo("pobi");
        assertThat(new CarName("junho").value()).isEqualTo("junho");
        assertThat(new CarName("pobi ").value()).isEqualTo("pobi");
    }
}
