package com.hackerRank;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramacke on 22-06-2014.
 */
public class TestMain {
    @Test
    public void testGetAndMinimum() throws IOException {
        // 1 2 3
        // (1,2), (1,3), (2,3) , (1,2,3)
        // (1&2) (1&3) (2&3) (1&2&3)

        List<Long> integers = new ArrayList<Long>();
        integers.add(1l);
        integers.add(1l);
        integers.add(1l);

        Assert.assertEquals(1, Solution.getAndMinimum(integers));
        integers.add(1l);
        Assert.assertEquals(1, Solution.getAndMinimum(integers));
        integers.add(1l);
        Assert.assertEquals(1, Solution.getAndMinimum(integers));

        Assert.assertEquals(0, Solution.getAndMinimum(Solution.getNumbersFromLine(3, "1 2 3")));

        Assert.assertEquals(0,2&2&2);

    }

}
