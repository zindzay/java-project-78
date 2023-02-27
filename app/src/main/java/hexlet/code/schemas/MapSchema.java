package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    @Override
    boolean isInstance(final Object value) {
        return value instanceof Map<?, ?>;
    }

    public MapSchema required() {
        this.addCondition(this::isNotNull);
        return this;
    }

    public MapSchema sizeof(final int size) {
        this.addCondition((Map<?, ?> map) -> map.size() == size);
        return this;
    }

    public MapSchema shape(final Map<?, BaseSchema> schemas) {
        this.addCondition(value -> schemas.entrySet()
                .stream()
                .allMatch(schema -> isValid(schema, value)));
        return this;
    }

    private boolean isValid(final Map.Entry<?, BaseSchema> schema, Map<?, ?> value) {
        return schema.getValue().isValid(value.get(schema.getKey()));
    }
}
