package com.kail;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class AppTest2 {

    @Test
    public void testNumericals(){
        System.out.println(MyEnum.ABC.toString());
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
    
    @Test
    public void characterToBit(){
        String str = "···· · −·−− ·−−− ··− −·· ·";
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        for(byte b : bytes) {
            System.out.print(" " + Integer.toBinaryString(b & 0xFF));
        }
    }
    
    public static String removeSpace(String input){
        return input.replaceAll("\\p{Space}", "");
    }
    
    public enum MyEnum{
        ABC
    }
    
    @Test
    public void testRegex(){

        String input = "SELECT  TOP " + "abc" + " DD1RA FROM ARG0CPP WITH   \n  " +
                "  (READUNCOMMITTED) WHERE DDR68A= :status   WITH (READUNCOMMITTED)";
        String output = input.replaceAll("WITH\\s+\\Q(READUNCOMMITTED)\\E", "");
        if (!output.isEmpty()) {
            System.out.println(output);
        }
        System.out.println("Finish");
    }
}
