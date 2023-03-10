package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final List<Predicate<T>> conditions = new LinkedList<>();

    abstract boolean isInstance(Object value);

    public final boolean isValid(final Object value) {
        if (isNotNull(value) && !isInstance(value)) {
            return false;
        }

        return conditions.stream().allMatch(condition -> condition.test((T) value));
    }

    protected final void addCondition(final Predicate<T> predicate) {
        conditions.add(predicate);
    }

    protected final boolean isNotNull(final Object value) {
        return value != null;
    }
}
