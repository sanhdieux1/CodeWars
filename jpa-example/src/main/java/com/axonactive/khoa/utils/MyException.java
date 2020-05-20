package com.axonactive.khoa.utils;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}
