package com.hackerRank.beginner;

import java.util.Scanner;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.sort;

/**
 * Created by ramacke on 25-06-2014.
 */
public class ServiceLane {
    private static final int ONE = 1;
    private static final int TWO = 2;

    /**
     * 2 <= numberOfTestCase <= 100000
     * 1 <= noOfLanes <= 1000
     * 0 <= i < j < N
     * 2 <= j-i+1 <= min(N,1000)
     * 1 <= width[k] <= 3, where 0 <= k < N
     * <p/>
     * the description is confusing, but the problem can be simplified into
     * - store the array
     * - find the minimum in the sub array, specified by the input
     */
    public static int getMinimumLane(int[] inLaneArray, int inSubArrayStart, int inSubArrayEnd) {
        // +1 to end, as the element is exclusive by definition
        int[] sortedArray = copyOfRange(inLaneArray, inSubArrayStart, inSubArrayEnd + 1);
        sort(sortedArray);
        return sortedArray[0];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTests;

        int noOfLanes = in.nextInt();
        numberOfTests = in.nextInt();
        int[] lanes = new int[noOfLanes];
        for (int i = 0; i < noOfLanes; ) {
            lanes[i] = in.nextInt();
            ++i;
        }
        int[] iCor = new int[numberOfTests];
        int[] jCor = new int[numberOfTests];

        for (int i = 0; i < numberOfTests; ) {
            iCor[i] = in.nextInt();
            jCor[i] = in.nextInt();
            ++i;
        }

        for (int i = 0; i < numberOfTests; ) {
            System.out.println(getMinimumLane(lanes, iCor[i], jCor[i]));
            ++i;
        }

    }
}
