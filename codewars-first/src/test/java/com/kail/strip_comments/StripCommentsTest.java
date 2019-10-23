package com.kail.strip_comments;

import org.junit.Assert;
import org.junit.Test;

public class StripCommentsTest {
    
    @Test
    public void testHappy(){
        Assert.assertEquals("apples, pears\ngrapes\nbananas", StripComments.stripComment("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"}));
    
        Assert.assertEquals(
                "a\nc\nd",
                StripComments.stripComment( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
        );
    }
}
