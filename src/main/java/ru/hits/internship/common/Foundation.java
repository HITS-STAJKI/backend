package ru.hits.internship.common;

public class Foundation {

    private Foundation() {}

    public static <T> T safeCast(Object object, Class<T> targetClass) {
        return targetClass.isInstance(object) ? targetClass.cast(object) : null;
    }
}
