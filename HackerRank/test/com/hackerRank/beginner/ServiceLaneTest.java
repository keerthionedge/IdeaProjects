package com.hackerRank.beginner;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ramacke on 25-06-2014.
 */
public class ServiceLaneTest {
    @Test
    public void testGetMinimumLane(){
        /**
         * 2 3 1 2 3 2 3 3
         0 3
         4 6
         6 7
         3 5
         0 7
         */
        int[] inputLane = {2, 3, 1, 2, 3, 2, 3, 3};
        Assert.assertEquals(1,ServiceLane.getMinimumLane(inputLane,0,3));
        Assert.assertEquals(2,ServiceLane.getMinimumLane(inputLane,4,6));
        Assert.assertEquals(3,ServiceLane.getMinimumLane(inputLane,6,7));
        Assert.assertEquals(2,ServiceLane.getMinimumLane(inputLane,3,5));
        Assert.assertEquals(1,ServiceLane.getMinimumLane(inputLane,0,7));
    }


}
