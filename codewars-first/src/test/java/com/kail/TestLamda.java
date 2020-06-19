package com.kail;

import lombok.Getter;
import org.junit.Test;

import java.util.Optional;

public class TestLamda {
    
    @Test
    public void testOptional(){
        MyObject a = new MyObject();
        String resoult = Optional.ofNullable(a)
                .map(MyObject::getAbc)
                .map(abc -> {System.out.println(abc); return abc;}).orElse("abc");
        System.out.println(resoult);
    }
    
    @Getter
    public class MyObject{
        private String abc;
        
    }
}
