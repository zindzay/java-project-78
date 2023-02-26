package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public final class StringSchema {
    private final List<Predicate<String>> predicates = new LinkedList<>();

    public boolean isValid(String value) {
        return predicates.stream().allMatch(p -> p.test(value));
    }

    public StringSchema required() {
        predicates.add((String value) -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        predicates.add((String value) -> value != null && value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        predicates.add((String value) -> value != null && value.contains(substring));
        return this;
    }
}
