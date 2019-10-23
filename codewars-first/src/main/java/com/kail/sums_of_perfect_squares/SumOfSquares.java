package com.kail.sums_of_perfect_squares;

import java.util.stream.IntStream;

public class SumOfSquares {
    public static int sum(int square, int n) {
        if (n == 0) {
            return n;
        }
        int count = 1;
        int extend = n - square * square;
        count += sum((int)Math.sqrt(extend), extend);
        return count;
    }
    
    public static int nSquaresFor(int n){
        int s = (int)Math.sqrt(n);
        return IntStream.range(3, s + 1).parallel().map(i -> SumOfSquares.sum(i, n)).min().getAsInt();
    }
}
