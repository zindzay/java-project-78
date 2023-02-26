package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    @Override
    boolean isInstance(Object value) {
        return value instanceof Integer;
    }

    public NumberSchema required() {
        this.addCondition(this::isNotNull);
        return this;
    }

    public NumberSchema positive() {
        this.addCondition((Integer number) -> number != null && number >= 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.addCondition((Integer number) -> number >= min && number <= max);
        return this;
    }
}
