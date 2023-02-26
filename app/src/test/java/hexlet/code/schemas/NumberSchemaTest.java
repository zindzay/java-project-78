package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    private Validator validator;
    private static final int MIN_NUMBER = -3;
    private static final int MAX_NUMBER = 10;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
    }

    @ParameterizedTest
    @MethodSource("getRequiredTestData")
    void requiredTest(Object value, boolean isValid) {
        assertThat(validator.number().required().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getPositiveTestData")
    void positiveTest(Object value, boolean isValid) {
        assertThat(validator.number().positive().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getRangeTestData")
    void rangeTest(Object value, boolean isValid) {
        assertThat(validator.number().range(MIN_NUMBER, MAX_NUMBER).isValid(value)).isEqualTo(isValid);
    }

    @Test
    void isValidTest() {
        assertThat(validator.number()
                .required()
                .positive()
                .range(MIN_NUMBER, MAX_NUMBER)
                .isValid(1)
        ).isTrue();

        assertThat(validator.number().isValid(null)).isTrue();
    }

    private static Stream<Arguments> getRequiredTestData() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(0, true),
                Arguments.of(1, true)
        );
    }

    private static Stream<Arguments> getPositiveTestData() {
        return Stream.of(
                Arguments.of(-1, false),
                Arguments.of(null, true),
                Arguments.of(0, false),
                Arguments.of(1, true)
        );
    }

    private static Stream<Arguments> getRangeTestData() {
        return Stream.of(
                Arguments.of(-1, true),
                Arguments.of(MIN_NUMBER, true),
                Arguments.of(1, true),
                Arguments.of(MAX_NUMBER, true),
                Arguments.of(0, true)
        );
    }
}
