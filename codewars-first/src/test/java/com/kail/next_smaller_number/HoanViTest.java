package com.kail.next_smaller_number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HoanViTest {
    
    @Test
    public void test(){
        assertEquals(12, Kata.nextSmaller(21));
        assertEquals(790, Kata.nextSmaller(907));
        assertEquals(513, Kata.nextSmaller(531));
        assertEquals(-1, Kata.nextSmaller(1027));
        assertEquals(414, Kata.nextSmaller(441));
        assertEquals(123456789, Kata.nextSmaller(123456798));
        System.out.println(Kata.nextSmaller(951123456789L));
    }
}
