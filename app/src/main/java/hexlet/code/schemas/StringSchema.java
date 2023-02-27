package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    @Override
    boolean isInstance(final Object value) {
        return value instanceof String;
    }

    public StringSchema required() {
        this.addCondition((String value) -> isNotNull(value) && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(final int minLength) {
        this.addCondition((String value) -> value.length() >= minLength);
        return this;
    }

    public StringSchema contains(final String substring) {
        this.addCondition((String value) -> value.contains(substring));
        return this;
    }
}
