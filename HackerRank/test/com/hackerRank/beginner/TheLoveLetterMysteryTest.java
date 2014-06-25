package com.hackerRank.beginner;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ramacke on 25-06-2014.
 */
public class TheLoveLetterMysteryTest {
    @Test
    public void testGetOperationCount(){
        Assert.assertEquals(2,TheLoveLetterMystery.getOperationCount("abc"));
        Assert.assertEquals(0,TheLoveLetterMystery.getOperationCount("abcba"));
        Assert.assertEquals(4,TheLoveLetterMystery.getOperationCount("abcd"));
    }
}
