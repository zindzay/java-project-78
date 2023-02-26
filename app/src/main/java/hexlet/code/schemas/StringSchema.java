package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    @Override
    boolean isInstance(Object value) {
        return value instanceof String;
    }

    public StringSchema required() {
        this.addCondition((String value) -> isNotNull(value) && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.addCondition((String value) -> value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        this.addCondition((String value) -> value.contains(substring));
        return this;
    }
}
