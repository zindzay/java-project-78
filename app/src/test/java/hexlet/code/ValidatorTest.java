package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {
    private StringSchema stringSchema;

    @BeforeEach
    public void beforeEach() {
        stringSchema = new Validator().string();
    }

    @ParameterizedTest
    @MethodSource("getRequiredTestData")
    void requiredTest(String s, boolean isValid) {
        assertThat(stringSchema.required().isValid(s)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getMinLengthTestData")
    void minLengthTest(String s, boolean isValid) {
        final int minLength = 3;
        assertThat(stringSchema.minLength(minLength).isValid(s)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getContainsTestData")
    void containsTest(String s, boolean isValid) {
        final String substring = "str";
        assertThat(stringSchema.contains(substring).isValid(s)).isEqualTo(isValid);
    }

    private static Stream<Arguments> getRequiredTestData() {
        return Stream.of(
                Arguments.of("", false),
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
                Arguments.of("string", true),
                Arguments.of("str", true),
                Arguments.of("s", false)
        );
    }

    private static Stream<Arguments> getContainsTestData() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of(null, false),
                Arguments.of("string", true),
                Arguments.of("str", true),
                Arguments.of("s", false),
                Arguments.of("строка", false)
        );
    }
}
