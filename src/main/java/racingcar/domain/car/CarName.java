package racingcar.domain.car;

import racingcar.view.UiText;

public class CarName {
    private static final int MAX_LENGTH = 5;

    private final String name;

    public CarName(String rawName) {
        String trimmedName = trim(rawName);
        validateLength(trimmedName);
        this.name = trimmedName;
    }

    public String value() {
        return name;
    }

    private String trim(String rawName) {
        if (rawName == null) {
            throw new IllegalArgumentException(UiText.Error.NAME_EMPTY);
        }
        String trimmed = rawName.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(UiText.Error.NAME_EMPTY);
        }
        return trimmed;
    }

    private void validateLength(String trimmedName) {
        if (trimmedName.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(UiText.Error.NAME_UNVALID_LENGTH);
        }
    }
}
