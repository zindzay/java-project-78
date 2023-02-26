package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ShapeMapSchemaTest {
    private Validator validator;
    private static final String KEY_1 = "name";
    private static final String KEY_2 = "age";

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    void isValidTest(Object value, boolean isValid) {
        final MapSchema schema = validator.map();
        final Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put(KEY_1, validator.string().required());
        schemas.put(KEY_2, validator.number().positive());
        schema.shape(schemas);

        assertThat(schema.isValid(value)).isEqualTo(isValid);
    }

    private static Stream<Arguments> getTestData() {
        final Map<String, Object> human1 = new HashMap<>();
        human1.put(KEY_1, "Kolya");
        human1.put(KEY_2, 1);

        final Map<String, Object> human2 = new HashMap<>();
        human2.put(KEY_1, "Maya");
        human2.put(KEY_2, null);

        final Map<String, Object> human3 = new HashMap<>();
        human3.put(KEY_1, "");
        human3.put(KEY_2, null);

        final Map<String, Object> human4 = new HashMap<>();
        human4.put(KEY_1, "Valya");
        human4.put(KEY_2, -1);

        return Stream.of(
                Arguments.of(human1, true),
                Arguments.of(human2, true),
                Arguments.of(human3, false),
                Arguments.of(human4, false)
        );
    }
}
