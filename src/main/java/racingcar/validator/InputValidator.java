package racingcar.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import racingcar.view.UiText;

public final class InputValidator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public List<String> validateCarNames(String rawInput) {
        String trimmedInput = trim(rawInput);
        List<String> names = splitByComma(trimmedInput);
        ensureNoEmptyName(names);
        return names;
    }

    public int validateAttemptCount(String rawInput) {
        String trimmedInput = trim(rawInput);
        ensureNumeric(trimmedInput);
        int attemptCount = Integer.parseInt(trimmedInput);
        if (attemptCount < 1) {
            throw new IllegalArgumentException(UiText.Error.ATTEMPT_TOO_SMALL);
        }
        return attemptCount;
    }

    private String trim(String input) {
        if (input == null) {
            throw new IllegalArgumentException(UiText.Error.EMPTY_INPUT);
        }
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(UiText.Error.EMPTY_INPUT);
        }
        return trimmed;
    }

    private List<String> splitByComma(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    private void ensureNoEmptyName(List<String> names) {
        if (names.isEmpty() || names.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException(UiText.Error.NAME_EMPTY);
        }
    }

    private void ensureNumeric(String input) {
        if (!NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(UiText.Error.NOT_NUMERIC);
        }
    }
}
