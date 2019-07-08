package com.kail;

import java.util.ArrayList;
import java.util.List;

public class MyCollector
    {
        List<Integer> data = new ArrayList<>();
        
        public MyCollector append(int input){
        data.add(input);
        return this;
    }
        public MyCollector appendCodePoint(int input){
        data.add(input);
        return this;
    }
}
