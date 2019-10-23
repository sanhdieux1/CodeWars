package com.kail.sums_of_perfect_squares;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumOfSquaresTest {
    
    @Test
    public void testHappy() {
        assertEquals(4, SumOfSquares.nSquaresFor(15));
        assertEquals(1, SumOfSquares.nSquaresFor(16));
        assertEquals(2, SumOfSquares.nSquaresFor(17));
        assertEquals(2, SumOfSquares.nSquaresFor(18));
        assertEquals(3, SumOfSquares.nSquaresFor(19));
        
        SumOfSquares.nSquaresFor((int) Math.pow(10, 9));
    }
}
