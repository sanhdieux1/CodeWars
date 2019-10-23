package com.kail.weight_for_weight;

import org.junit.Assert;
import org.junit.Test;

public class WeightTest {
    
    @Test
    public void test(){
        Assert.assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999", Weight.sort("2000 10003 1234000 44444444 9999 11 11 22 123"));
    }
}
