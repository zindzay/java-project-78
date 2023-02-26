package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    @Override
    boolean isInstance(Object value) {
        return value instanceof Map<?, ?>;
    }

    public MapSchema required() {
        this.addCondition(this::isNotNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.addCondition((Map<?, ?> map) -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema<?>> schemas) {
        this.addCondition(value -> schemas.entrySet()
                .stream()
                .allMatch(schemaEntry -> isValid(schemaEntry, value)));
        return this;
    }

    private boolean isValid(Map.Entry<?, BaseSchema<?>> schemaEntry, Map<?, ?> value) {
        return schemaEntry.getValue().isValid(value.get(schemaEntry.getKey()));
    }
}
