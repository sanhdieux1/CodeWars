package com.axonactive.khoa.beans;

import com.axonactive.khoa.beans.technical.Mapping;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class FieldInConverter extends FieldConverter {
    
    public FieldInConverter(Object target, Object source, Field mappingField, Mapping mapping) {
        super(target, source, mappingField, mapping);
    }
    
    @Override
    protected String getSourceFieldName() {
        String sourceFieldName = mapping.name();
        if (StringUtils.isBlank(sourceFieldName)) {
            sourceFieldName = mappingField.getName();
        }
        return sourceFieldName;
    }
    @Override
    protected String getTargetFieldName() {
        return mappingField.getName();
    }
    
    @Override
    protected Class getTargetCollectionElementType() throws NoSuchFieldException {
        Field targetField = target.getClass().getDeclaredField(getTargetFieldName());
        ParameterizedType targetElementTypes = (ParameterizedType) targetField.getGenericType();
        return (Class) targetElementTypes.getActualTypeArguments()[0];
    }
    @Override
    protected Class<?> getTargetType() throws NoSuchFieldException {
        Field targetField = target.getClass().getDeclaredField(getTargetFieldName());
        return targetField.getType();
    }
}
