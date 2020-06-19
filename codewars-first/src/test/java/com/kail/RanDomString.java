package com.kail;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class RanDomString {
    @Test
    public void givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
        
        System.out.println(RandomStringUtils.randomAlphabetic(10));
    }
}
