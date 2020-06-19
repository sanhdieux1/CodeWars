package com.kail;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyCollector {
        List<Integer> data = new ArrayList<>();
        
        public MyCollector append(int input){
        data.add(input);
        return this;
    }
        public MyCollector appendCodePoint(int input){
        data.add(input);
        return this;
    }
    
    @Test
    public void test(){
            Integer a = null;
            if(Integer.valueOf(1) == a) {
                System.out.println("true");
            }
    }
    
}
