package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

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

    @Test
    void requiredTest() {
        assertThat(validator.map().required().isValid(null)).isFalse();
        assertThat(validator.map().required().isValid("null")).isFalse();
        assertThat(validator.map().required().isValid(Map.of())).isTrue();
        assertThat(validator.map().required().isValid(SIMPLE_MAP)).isTrue();
    }

    @Test
    void sizeTest() {
        assertThat(validator.map().sizeof(SIZE).isValid(Map.of())).isFalse();
        assertThat(validator.map().sizeof(SIZE).isValid(Map.of("key1", "value1"))).isFalse();
        assertThat(validator.map().sizeof(SIZE).isValid(SIMPLE_MAP)).isTrue();
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
}
