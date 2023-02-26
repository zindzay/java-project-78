package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final List<Predicate<T>> conditions = new LinkedList<>();

    abstract boolean isInstance(Object value);

    public final boolean isValid(Object value) {
        if (!isInstance(value)) {
            return false;
        }

        final T valueWithType = (T) value;
        return isNotNull(valueWithType) && conditions.stream().allMatch(condition -> condition.test(valueWithType));
    }

    protected final void addCondition(Predicate<T> predicate) {
        conditions.add(predicate);
    }

    protected final boolean isNotNull(T value) {
        return value != null;
    }
}
