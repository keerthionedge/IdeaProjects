package com.hackerRank.beginner;

import java.util.*;

/**
 * Created by ramacke on 25-06-2014.
 */
public class UtopianTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTests;
        numberOfTests = in.nextInt();
        List<Integer> integerList = new ArrayList<Integer>(numberOfTests);
        while (numberOfTests > 0) {
            integerList.add(in.nextInt());
            --numberOfTests;
        }
        Map<Integer, Integer> utopianTreeMap = getUtopianTree();
        for (Integer integer : integerList) {
            System.out.println(utopianTreeMap.get(integer));
        }

    }

    /**
     * 1 <= noOfTests <= 10
     * 0 <= noOfRequestedGrowth <= 60
     * <p/>
     * for odd values of noOfRequestedGrowth x2
     * for even values of noOfRequestedGrowth +1
     * i.e.,
     * 0   1   2   3   4   5   6   7   8   9
     * 1   2   3   6   7   14  15  30  31  62
     * <p/>
     * instead of map use array for faster/lightweight access
     */
    public static Map<Integer, Integer> getUtopianTree() {
        Map<Integer, Integer> utopianTreeMap = new HashMap<Integer, Integer>();
        utopianTreeMap.put(0, 1); // to avoid arithmatic exception add 0 as we use divide
        int counter = 1;
        for (int i = 1; i <= 60; ) {
            if (i % 2 == 1) {
                counter *= 2;
            } else {
                counter += 1;
            }
            utopianTreeMap.put(i, counter);
            ++i;
        }

        return utopianTreeMap;
    }

}
