package com.axonactive.khoa.beans.technical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mapping {
    
    /**
     * Specifies the other attribute name. Only needed if it differs from the source name.
     */
    String name() default "";
    
    /**
     * Specifies the converter class to be used to convert the object from other to target.
     */
    Class<? extends ECMConverter> converter() default ECMConverter.class;
    
    /**
     * Only affect whenever the target and source are the same classes.
     * Specifies instance in case the target and source are the same class type.
     * False by default, it means always try to convert source to new target instance.
     * True, simple get from source and set again to target object. If field type inherit from {@link java.util.Collection}, then return new collection then copy all reference items inside.
     *
     */
    boolean keepInstance() default false;
}
