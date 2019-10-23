package com.kail.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void testHappyCase(){
        assertEquals(127, Calculator.evaluate("127"), 0.01);
        assertEquals(5, Calculator.evaluate("2 + 3"), 0.01);
        assertEquals(15, Calculator.evaluate("2 + 2 * 4 + 5"), 0.01);
        assertEquals(-5, Calculator.evaluate("2 - 3 - 4"), 0.01);
        assertEquals(10, Calculator.evaluate("10 * 5 / 5"), 0.01);
    }
}
