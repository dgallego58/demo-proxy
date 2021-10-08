package co.com.demo.proxy.aop;

import java.util.function.UnaryOperator;

public class UtilValidator<T> {

    private final UnaryOperator<T> obj = t -> {
        if (t == null) {
            throw new RuntimeException("a");
        }
        return t;
    };

    private final T object;

    private UtilValidator(T obj) {
        this.object = obj;
    }

    public static <T> UtilValidator<T> create(T object) {
        return new UtilValidator<>(object);
    }

    public T nullOrElse() {
        return obj.apply(this.object);
    }

}
