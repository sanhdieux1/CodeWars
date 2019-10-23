package com.kail.prime_streaming;

import java.util.BitSet;
import java.util.function.IntSupplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Primes {
    
    public static Logger logger = Logger.getLogger("logger");
    
    public static IntStream stream2() {
        long start = System.currentTimeMillis();
        IntStream rs = IntStream.range(1, Integer.MAX_VALUE).filter(Primes::laSoNguyenTo2);
        long end = System.currentTimeMillis();
        logger.log(Level.INFO, "Time caculate: {0}", (end - start));
        return rs;
    }
    
    
    public static boolean laSoNguyenTo2(int n) {
        if (n < 2) {
            return false;
        }
        
        if (n == 2) {
            return true;
        }
        
        if (n % 2 == 0) {
            return false;
        }
        int k = (int) Math.sqrt(n);
        for (int i = 3; i <= k; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    
    public static IntStream stream() {
        return IntStream.generate(
                new IntSupplier() {
                    int lastSeen = 1;
                    
                    public int getAsInt() {
                        int i = lastSeen;
                        while (!isPrime(++i)) {
                        }
                        lastSeen = i;
                        return i;
                    }
                });
    }
    
    public static boolean isPrime(int i) {
        if (i == 1)
            return false;
        if (i == 2 || i == 3 || i == 5 || i == 7 || i == 11 || i == 13 || i == 17 || i == 19)
            return true; // edge cases
        if (i % 2 == 0 || i % 3 == 0)
            return false; // edge cases
        int endp = (int) Math.ceil(Math.sqrt(i) / 6.0) + 2;
        for (int m = 1; m <= endp; m++) {
            if (i % (6 * m + 1) == 0 || i % (6 * m - 1) == 0)
                return false;
        }
        return true;
    }
    
    
    private static final BitSet erastoteme = new BitSet(10000000);
    static {
        erastoteme.flip(0, erastoteme.size()-1);
        erastoteme.clear(0);
        erastoteme.clear(1);
        for(int prime=2; prime>0 && prime<erastoteme.size(); prime=erastoteme.nextSetBit(prime+1)) {
            for(int i=prime+prime; i<erastoteme.size(); i+=prime) erastoteme.clear(i);
        }
    }
    
    public static IntStream stream3() {
        return erastoteme.stream();
    }
    
}
