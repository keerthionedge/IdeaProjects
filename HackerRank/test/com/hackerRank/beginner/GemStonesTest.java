package com.hackerRank.beginner;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Keerthi on 25-06-2014.
 */
public class GemStonesTest {
    @Test
    public void testIntersectCount(){

        Assert.assertEquals(2,GemStones.intersectCount(new String[]{"abcdde","baccd","eeabg"},3));
    }
}
