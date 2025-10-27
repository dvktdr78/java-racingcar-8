package racingcar.view;

public final class UiText {
    private UiText() {}

    public static final class Prompt {
        private Prompt() {}

        public static final String CAR_NAMES =
                "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
        public static final String ATTEMPT_COUNT =
                "시도할 횟수는 몇 회인가요?";
    }

    public static final class Output {
        private Output() {}

        public static final String EXECUTION_RESULT_HEADER = "실행 결과";
        public static final String WINNER_PREFIX = "최종 우승자 : ";
        public static final String POSITION_MARK = "-";
    }

    public static final class Error {
        private Error() {}

        public static final String PREFIX = "[ERROR] ";
        public static final String UNKNOWN = "알 수 없는 오류가 발생했습니다.";
        public static final String EMPTY_INPUT = "입력값은 비어 있을 수 없습니다.";
        public static final String NOT_NUMERIC = "시도 횟수는 숫자여야 합니다.";
        public static final String ATTEMPT_TOO_SMALL = "시도 횟수는 1 이상의 정수여야 합니다.";
        public static final String NAME_EMPTY = "자동차 이름은 비어 있을 수 없습니다.";
        public static final String NAME_UNVALID_LENGTH = "자동차 이름은 1자 이상 5자 이하여야 합니다.";
        public static final String NAME_DUPLICATED = "자동차 이름은 중복될 수 없습니다.";
        public static final String CAR_COUNT_TOO_SMALL = "경주에는 최소 2대 이상의 자동차가 필요합니다.";
    }
}
