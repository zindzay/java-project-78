package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {
    private StringSchema stringSchema;
    private static final int MIN_LENGTH = 3;
    private static final String SUBSTRING = "str";

    @BeforeEach
    public void beforeEach() {
        stringSchema = new Validator().string();
    }

    @ParameterizedTest
    @MethodSource("getRequiredTestData")
    void requiredTest(Object value, boolean isValid) {
        assertThat(stringSchema.required().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getMinLengthTestData")
    void minLengthTest(Object value, boolean isValid) {
        assertThat(stringSchema.minLength(MIN_LENGTH).isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getContainsTestData")
    void containsTest(Object value, boolean isValid) {
        assertThat(stringSchema.contains(SUBSTRING).isValid(value)).isEqualTo(isValid);
    }

    @Test
    void isValidTest() {
        assertThat(stringSchema
                .required()
                .contains(SUBSTRING)
                .minLength(MIN_LENGTH)
                .isValid("string")
        ).isTrue();
    }

    private static Stream<Arguments> getRequiredTestData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(1, false),
                Arguments.of(null, false),
                Arguments.of("string", true),
                Arguments.of("str", true),
                Arguments.of("s", true)
        );
    }

    private static Stream<Arguments> getMinLengthTestData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(null, false),
                Arguments.of(1, false),
                Arguments.of("string", true),
                Arguments.of("str", true),
                Arguments.of("s", false)
        );
    }

    private static Stream<Arguments> getContainsTestData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(null, false),
                Arguments.of(1, false),
                Arguments.of("string", true),
                Arguments.of("str", true),
                Arguments.of("s", false),
                Arguments.of("строка", false)
        );
    }
}
