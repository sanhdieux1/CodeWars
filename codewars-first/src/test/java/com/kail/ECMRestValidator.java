package com.kail;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ECMRestValidator {
    
    
    public static void validateRequestInput(Object object) {
        if(Objects.isNull(object)) {
            throw new RuntimeException(String.format("Invalid or missing input"));
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.size() > 0) {
            String errorMessage = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            throw new RuntimeException(errorMessage);
        }
    }
    
    public static void validateRequiredRequestParam(Object input, String paramName) {
        if(Objects.isNull(input)) {
            throw new RuntimeException(String.format("Invalid or missing parameter(s):%s", paramName));
        }
    }
    
    
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
    
        String patter = "^[a-zA-Z0-9]@[a-zA-Z0-9]$";
        System.out.println(Pattern.matches(patter, "abc@xyz"));
//        ECMRestValidator.validateRequestInput(myObject);
    }
    
    
    @Getter
    @Setter
    public static class MyObject{
        @NotBlank(message = "can not be empty")
        private String notEmpty;
    }
}
