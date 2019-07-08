package com.axonactive.khoa.utils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class CopyUtil {

    private static final Logger LOGGER = Logger.getLogger(CopyUtil.class.getName());
    
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
                    LOGGER.log(Level.WARNING, String.format("CANN'T FIND FIELD NAME: %s, IN model: %s", field.getName(), from.getClass().getName()));
                }
            });
            return result;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, String.format("CANN'T CONVERT model: %s", from.getClass().getName()));
        }
        return null;
    }
}
