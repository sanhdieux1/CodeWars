package com.axonactive.khoa.beans.technical;

public interface ECMConverter<T, U> {
    public U convert(Object source, Object target, T sourceField);
}
