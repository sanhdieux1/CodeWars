package com.kail;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppTest2 {

    @Test
    public void testNumericals(){

        System.out.println(convertFracs(new long[][] { {1, 2}, {1, 3}, {1, 4} }));
        System.out.println(convertFracs(new long[][] {}));
    }

    public static String convertFracs(long[][] lst){
        long D = Arrays.stream(lst).map(d -> d[1]).reduce((d1, d2) -> ucln(d1,d2)
        ).orElse(1L);
        return Arrays.stream(lst).map(d -> new long[]{D * d[0] / d[1], D}).map(d -> String.format("(%d,%d)", d[0], d[1])).collect(Collectors.joining());

    }

    public static long ucln(long a, long b){
        long t = a; long m =b;
        //t,m UCLN
        while (t!=m)
        {
            if(t>m)
                t=t-m;
            else
                m=m-t;
        }
        //return BCNN
        return a*b/t;
    }
}
