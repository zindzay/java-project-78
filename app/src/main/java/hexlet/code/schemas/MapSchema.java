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
}
