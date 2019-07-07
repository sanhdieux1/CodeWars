package com.axonactive.khoa.utils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

public class CopyUtil {

    public static <T> T copy(Object from, Class<T> to) {
        if (Objects.isNull(from)) {
            return null;
        }

        try {
            T result = to.newInstance();
            Stream.of(from.getClass().getDeclaredFields()).forEach(field -> {
                try {
                    field.setAccessible(true);
                    Field toField = to.getDeclaredField(field.getName());
                    toField.setAccessible(true);
                    Object value = field.get(from);
                    if(value != null) {
                        String packageName = value.getClass().getPackage().getName();
                        if (packageName.startsWith("com.axonactive")) {
                            Class<?> toClass = toField.getType();
                            value = copy(value, toClass);
                        }
                        toField.set(result, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
