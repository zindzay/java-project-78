package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    @CsvSource(value = {", false", "0, true", "1, true"})
    void requiredTest(final Integer value, final boolean isValid) {
        assertThat(validator.number().required().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1, false", ", true", "0, false", "1, true"})
    void positiveTest(final Integer value, final boolean isValid) {
        assertThat(validator.number().positive().isValid(value)).isEqualTo(isValid);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1, true", "-3, true", "1, true", "10, true", "0, true"})
    void rangeTest(final Integer value, final boolean isValid) {
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
}
