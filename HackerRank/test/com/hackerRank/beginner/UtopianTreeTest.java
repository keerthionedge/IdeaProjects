package com.hackerRank.beginner;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by ramacke on 25-06-2014.
 */
public class UtopianTreeTest {
    @Test
    public void testGetUtopianTree(){
        Map<Integer,Integer> utopianTreeMap =UtopianTree.getUtopianTree();
        Assert.assertEquals(new Integer(1),utopianTreeMap.get(0));
        Assert.assertEquals(new Integer(2),utopianTreeMap.get(1));
        Assert.assertEquals(new Integer(3),utopianTreeMap.get(2));
        Assert.assertEquals(new Integer(6),utopianTreeMap.get(3));
        /*Assert.assertEquals(2,3);
        Assert.assertEquals(3,6);
        Assert.assertEquals(4,7);*/
    }
}
