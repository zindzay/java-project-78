package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {
    private Validator validator;
    private static final int SIZE = 2;
    private static final Map<String, String> SIMPLE_MAP = Map.of(
            "key1", "value1",
            "key2", "value2"
    );

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
    }

    @ParameterizedTest
    @MethodSource("getRequiredTestData")
    void requiredTest(final Object value, final boolean isValid) {
        assertThat(validator.map().required().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @MethodSource("getSizeTestData")
    void sizeTest(final Object value, final boolean isValid) {
        assertThat(validator.map().sizeof(SIZE).isValid(value)).isEqualTo(isValid);
    }

    @Test
    void isValidTest() {
        assertThat(validator.map()
                .required()
                .sizeof(SIZE)
                .isValid(SIMPLE_MAP)
        ).isTrue();

        assertThat(validator.map().isValid(null)).isTrue();
    }

    private static Stream<Arguments> getRequiredTestData() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(Map.of(), true),
                Arguments.of(SIMPLE_MAP, true)
        );
    }

    private static Stream<Arguments> getSizeTestData() {
        return Stream.of(
                Arguments.of(Map.of(), false),
                Arguments.of(Map.of("key1", "value1"), false),
                Arguments.of(SIMPLE_MAP, true)
        );
    }
}
