package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {
    private Validator validator;
    private static final int MIN_LENGTH = 3;
    private static final String SIMPLE_STRING = "what does the fox say";
    private static final String FIRST_SUBSTRING = "wh";
    private static final String SECOND_SUBSTRING = "whatthe";

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
    }

    @ParameterizedTest
    @MethodSource("getRequiredTestData")
    void requiredTest(Object value, boolean isValid) {
        assertThat(validator.string().required().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getMinLengthTestData")
    void minLengthTest(Object value, boolean isValid) {
        assertThat(validator.string().minLength(MIN_LENGTH).isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getContainsTestData1")
    void containsTest1(Object value, boolean isValid) {
        assertThat(validator.string().contains(FIRST_SUBSTRING).isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getContainsTestData2")
    void containsTest2(Object value, boolean isValid) {
        assertThat(validator.string().contains(FIRST_SUBSTRING).contains(SECOND_SUBSTRING).isValid(value))
                .isEqualTo(isValid);
    }

    @Test
    void isValidTest() {
        assertThat(validator.string()
                .required()
                .contains(FIRST_SUBSTRING)
                .minLength(MIN_LENGTH)
                .isValid(SIMPLE_STRING)
        ).isTrue();

        assertThat(validator.string().isValid(null)).isTrue();
    }

    private static Stream<Arguments> getRequiredTestData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(1, false),
                Arguments.of(null, false),
                Arguments.of(SIMPLE_STRING, true),
                Arguments.of("s", true)
        );
    }

    private static Stream<Arguments> getMinLengthTestData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(SIMPLE_STRING, true),
                Arguments.of("str", true),
                Arguments.of("s", false)
        );
    }

    private static Stream<Arguments> getContainsTestData1() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(SIMPLE_STRING, true),
                Arguments.of("s", false),
                Arguments.of("строка", false)
        );
    }

    private static Stream<Arguments> getContainsTestData2() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(SIMPLE_STRING, false),
                Arguments.of("s", false),
                Arguments.of("строка", false)
        );
    }
}
