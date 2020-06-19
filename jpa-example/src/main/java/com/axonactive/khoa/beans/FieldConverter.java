package com.axonactive.khoa.beans;

import com.axonactive.khoa.beans.technical.ECMConverter;
import com.axonactive.khoa.beans.technical.Mapping;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public abstract class FieldConverter {
    
    private static final Map<Class, ECMConverter> TRANSLATORS_FOR_MUTABLE_TYPES = new HashMap<>();
    
    static {
        TRANSLATORS_FOR_MUTABLE_TYPES.put(BigDecimal.class, (ECMConverter<BigDecimal, BigDecimal>) (source, target, sourceField) -> new BigDecimal(sourceField.unscaledValue(), sourceField.scale()));
        TRANSLATORS_FOR_MUTABLE_TYPES.put(Date.class, (ECMConverter<Date, Date>) (source, target, sourceField) -> new Date(sourceField.getTime()));
        TRANSLATORS_FOR_MUTABLE_TYPES.put(Locale.class, (ECMConverter<Locale, Locale>) (source, target, sourceField) -> (Locale) sourceField.clone());
    }
    protected final Field mappingField;
    protected final Object target;
    protected final Object source;
    protected final Mapping mapping;
    
    public FieldConverter(Object target, Object source, Field mappingField, Mapping mapping) {
        this.target = target;
        this.source = source;
        this.mappingField = mappingField;
        this.mapping = mapping;
    }
    
    public void handleProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, NoSuchFieldException {
        Object sourceValue = getSourceValue();
        Field sourceFieldType = source.getClass().getDeclaredField(getSourceFieldName());
        Class<?> targetType = getTargetType();
        assignTargetValue(getTargetValue(sourceValue, sourceFieldType.getType(), targetType, mapping.converter()));
    }
    
    abstract Class<?> getTargetType() throws NoSuchFieldException;
    
    abstract String getSourceFieldName();
    
    abstract String getTargetFieldName();
    
    abstract Class getTargetCollectionElementType() throws NoSuchFieldException;
    
    private void assignTargetValue(Object value) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String targetFieldName = getTargetFieldName();
        if(isBooleanType()) {
            try {
                PropertyUtils.setProperty(target, targetFieldName, value);
            } catch (NoSuchMethodException ex) {
                //in case isBoolean
                PropertyUtils.setProperty(target, removeIsCharacter(targetFieldName), value);
            }
        } else {
            PropertyUtils.setProperty(target, targetFieldName, value);
        }
    }
    
    private Object getSourceValue() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String sourceFieldName = getSourceFieldName();
        Object sourceValue;
        if(isBooleanType()) {
            try {
                sourceValue = PropertyUtils.getProperty(source, sourceFieldName);
            } catch (NoSuchMethodException ex) {
                //in case isBoolean
                sourceValue = PropertyUtils.getProperty(source, removeIsCharacter(sourceFieldName));
            }
        } else {
            sourceValue = PropertyUtils.getProperty(source, sourceFieldName);
        }
        return sourceValue;
    }
    
    private Object getTargetValue(Object sourceValue, Class<?> sourceType, Class<?> targetType, Class<? extends ECMConverter> translatorType)
            throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        if (sourceValue == null) {
            return null;
        }
        Object targetValue = null;
        ECMConverter<?, ?> translator = getConverter(translatorType);
        if (translator != null) {
            targetValue = convertByConverter(sourceValue, translator);
        } else if (isImmutableType(targetType) || targetType.isEnum()) {
            targetValue = sourceValue;
        } else if (Collection.class.isAssignableFrom(targetType)) {
            Class<?> targetElementType = getTargetCollectionElementType();
            targetValue = getValueFromCollectionType(sourceValue, targetType, targetElementType);
        } else if(isKeepInstance(sourceType, targetType)) {
            targetValue = sourceValue;
        } else {
            ECMConverter mutableTypeTranslator = TRANSLATORS_FOR_MUTABLE_TYPES.get(targetType);
            targetValue = convertForMutableType(sourceValue, targetType, mutableTypeTranslator);
        }
        return targetValue;
    }
    
    private Object convertForMutableType(Object sourceValue, Class<?> targetType, ECMConverter mutableTypeTranslator) {
        Object targetValue;
        if (mutableTypeTranslator != null) {
            targetValue = mutableTypeTranslator.convert(source, target, sourceValue);
        } else {
            targetValue = BeanConverter.convert(sourceValue, targetType);
        }
        return targetValue;
    }
    
    private Object getValueFromCollectionType(Object sourceValue, Class targetType, Class targetElementType)
            throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Object targetValue;
        Collection sourceCollection = (Collection) sourceValue;
        Collection targetCollection = newCollectionInstance(targetType);
        for (Object sourceElementValue : sourceCollection) {
            Object targetElementValue = null;
            if(sourceElementValue != null) {
                targetElementValue = getTargetValue(sourceElementValue, sourceElementValue.getClass(), targetElementType, mapping.converter());
            }
            targetCollection.add(targetElementValue);
        }
        targetValue = targetCollection;
        return targetValue;
    }
    
    private Object convertByConverter(Object sourceValue, ECMConverter converter) {
        return converter.convert(source, target, sourceValue);
    }
    
    public static <T extends Collection> Collection newCollectionInstance(Class<T> clazz) {
        return Set.class.isAssignableFrom(clazz) ? new HashSet<>() : new ArrayList<>();
    }
    
    static ECMConverter getConverter(Class<? extends ECMConverter> translatorType) throws IllegalAccessException, InstantiationException {
        if (translatorType != null && (!translatorType.equals(ECMConverter.class))) {
            return translatorType.newInstance();
        }
        return null;
    }
    
    static boolean isImmutableType(Class type) {
        return type.isPrimitive()
                || type == Double.class
                || type == Float.class
                || type == Long.class
                || type == Integer.class
                || type == Short.class
                || type == Character.class
                || type == Byte.class
                || type == Boolean.class
                || type == String.class
                || type == LocalDate.class
                || type == LocalTime.class
                || type == Instant.class
                || type == LocalDateTime.class;
    }
    
    private boolean isBooleanType() {
        return mappingField.getType() == Boolean.TYPE;
    }
    
    private boolean isKeepInstance(Class<?> sourceType, Class<?> targetType) {
        return sourceType == targetType && mapping.keepInstance();
    }
    
    private static Field getFieldFromObject(Object objectSource, String fieldName) throws NoSuchFieldException {
        return objectSource.getClass().getDeclaredField(fieldName);
    }
    
    public static String removeIsCharacter(String fieldName) {
        if (fieldName == null || fieldName.length() <= 2) {
            return fieldName;
        }
        
        if(fieldName.startsWith("is")) {
            String fieldReplaced = fieldName.replaceFirst("is", "");
            char c[] = fieldReplaced.toCharArray();
            c[0] = Character.toLowerCase(c[0]);
            return new String(c);
        }
        return fieldName;
    }
}
