package com.hackerRank.beginner;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Keerthi on 25-06-2014.
 */
public class GemStones {
    /**
     * 1 ≤ noOfTests≤ 100
     *  Each composition consists of only small latin letters ('a'-'z').
     *  1 ≤ Length of each composition ≤ 100
     */
    /**
     * find common element in the given input., i.e intersection of the sets
     * input1 intersect input2 intersect .... input n
     * <p/>
     * initialize a ..... z in an array | using ascii (discard
     * remove one by one for each iteration
     */

    public static int intersectCount(String[] inGemStrings, int numberOfGems) {

        String initArray = "abcdefghijklmnopqrstuvwxyz";

        byte[] bytes = initArray.getBytes();
        byte[] booleans = new byte[bytes[bytes.length - 1] + 1];
        Arrays.fill(booleans, (byte) 0);

        for (String byteSet : inGemStrings) {
            byte[] bytes1 = byteSet.getBytes();

            byte[] dummy = new byte[bytes[bytes.length - 1] + 1];
            Arrays.fill(dummy, (byte) 0);
            for (Byte aByte : bytes1) {
                dummy[aByte] += 1;
            }
            for (int i = bytes[0]; i <= bytes[bytes.length - 1]; ) {
                if (dummy[i] >= 1) {
                    booleans[i] += 1;
                }

                ++i;
            }

        }
        int counter = 0;
        for (byte byteSet : bytes) {
            if (booleans[byteSet] == numberOfGems) {
                counter += 1;
            }
        }


        return counter;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTests = in.nextInt();
        String[] strings = new String[numberOfTests];
        for (int i = 0; i < numberOfTests; ) {
            strings[i] = in.next();
            ++i;
        }

        System.out.println(intersectCount(strings, numberOfTests));

    }
}
