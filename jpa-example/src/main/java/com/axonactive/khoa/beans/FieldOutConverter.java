package com.axonactive.khoa.beans;

import com.axonactive.khoa.beans.technical.Mapping;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class FieldOutConverter extends FieldConverter {
    public FieldOutConverter(Object target, Object source, Field targetField, Mapping mapping) {
        super(target, source, targetField, mapping);
    }
    
    @Override
    protected String getSourceFieldName() {
        return mappingField.getName();
    }
    
    @Override
    protected String getTargetFieldName() {
        String targetFieldName = mapping.name();
        if (StringUtils.isBlank(targetFieldName)) {
            targetFieldName = mappingField.getName();
        }
        return targetFieldName;
    }
    
    @Override
    protected Class getTargetCollectionElementType() {
        ParameterizedType targetElementTypes = (ParameterizedType) mappingField.getGenericType();
        return (Class) targetElementTypes.getActualTypeArguments()[0];
    }
    
    @Override
    protected Class<?> getTargetType() {
        return mappingField.getType();
    }
}
