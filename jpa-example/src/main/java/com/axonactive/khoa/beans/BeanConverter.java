package com.axonactive.khoa.beans;

import ch.xpertline.xecm.data.interfaces.technical.Mapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanConverter {
    /**
     *
     * @param source the source object to use for this mapping.
     * @param targetClass The target class that expect to be created. This class should contain all mapping definitions. See {@link Mapping}
     * @param <T> The type of the target class to create.
     * @return Instance of targetClass's type.
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            return convert(source, target);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param source the source object to use for this mapping. This class should contain all mapping definitions. See {@link Mapping}
     * @param targetClass The target class that expect to be created.
     * @param <T> The type of the target class to create.
     * @return Instance of targetClass's type.
     */
    public static <T> T convertOut(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            return convertOut(source, target);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static <T, U> List<T> convertOutList(List<U> sources, Class<T> targetClass) {
        List<T> convertedCollection = new ArrayList<>();
        for(Object source : sources) {
            convertedCollection.add(convertOut(source, targetClass));
        }
        return convertedCollection;
    }
    
    public static <T, U> List<T> convertList(List<U> sources, Class<T> targetClass) {
        List<T> convertedCollection = new ArrayList<>();
        for(Object source : sources) {
            convertedCollection.add(convert(source, targetClass));
        }
        return convertedCollection;
    }
    
    private static <T> T convert(Object source, T target) {
        if (source == null) {
            return target;
        }
        try {
            List<Field> targetFieldList = getDeclaredFieldsInHierarchy(target);
        
            for (Field targetField : targetFieldList) {
                Mapping mapped = getMapping(targetField);
                if (mapped != null) {
                    ch.xpertline.xecm.data.converters.bean.FieldConverter fc = new ch.xpertline.xecm.data.converters.bean.FieldInConverter(target, source, targetField, mapped);
                    fc.handleProperty();
                }
            }
            return target;
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static <T> T convertOut(Object source, T target) {
        if (source == null) {
            return target;
        }
        try {
            List<Field> targetFieldList = getDeclaredFieldsInHierarchy(source);
            
            for (Field targetField : targetFieldList) {
                Mapping mapped = getMapping(targetField);
                if (mapped != null) {
                    ch.xpertline.xecm.data.converters.bean.FieldConverter fc = new FieldOutConverter(target, source, targetField, mapped);
                    fc.handleProperty();
                }
            }
            return target;
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static <T> Mapping getMapping(Field field) {
        return field.getAnnotation(Mapping.class);
    }
    
    private static <T> List<Field> getDeclaredFieldsInHierarchy(T target) throws SecurityException {
        List<Field> targetFieldList = new ArrayList<>();
        Class<?> currentClass = target.getClass();
        while (currentClass != null && !currentClass.equals(Object.class)) {
            targetFieldList.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return targetFieldList;
    }
}
