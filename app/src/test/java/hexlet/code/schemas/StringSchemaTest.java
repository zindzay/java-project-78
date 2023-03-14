package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @CsvSource(value = {", false", "null, false", "what does the fox say, true", "s, true"}, nullValues = "null")
    void requiredTest(final String value, final boolean isValid) {
        assertThat(validator.string().required().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @CsvSource(value = {"'', false", "what does the fox say, true", "s, false", "строка, false"})
    void containsTest1(final Object value, final boolean isValid) {
        assertThat(validator.string().contains(FIRST_SUBSTRING).isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @CsvSource(value = {"'', false", "what does the fox say, true", "str, true", "s, false"})
    void minLengthTest(final String value, final boolean isValid) {
        assertThat(validator.string().minLength(MIN_LENGTH).isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @CsvSource(value = {"'', false", "what does the fox say, false", "s, false", "строка, false"})
    void containsTest2(final Object value, final boolean isValid) {
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
}
