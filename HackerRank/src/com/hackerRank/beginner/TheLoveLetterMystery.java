package com.hackerRank.beginner;

import java.util.Scanner;

/**
 * Created by ramacke on 25-06-2014.
 */
public class TheLoveLetterMystery {
    /**
     * 1 ≤ T ≤ 10
     *  1 ≤ length of string ≤ 10^4
     *  All characters are lower cased english alphabets.
     */
    /**
     * assign as follows
     * a b c d e f g h ... z as
     * 1 2 3 4 5 6 7 8.... 26          | ascii values can be also used which is better
     * so converting them to another - the required no of ops will be their diff
     */

    public static int getOperationCount(String inCharSequence) {
        byte[] bytes = inCharSequence.getBytes();
        int noOfOps = 0;
        int startPos = 0;
        int endPos = inCharSequence.length() - 1;
        while (startPos <= endPos) {
            int operationDiff = 0;
            if(bytes[endPos] >= bytes[startPos]){
                operationDiff = bytes[endPos] - bytes[startPos];
            } else{
                operationDiff = bytes[startPos] - bytes[endPos];
            }
            noOfOps += operationDiff;
            ++startPos;
            --endPos;
        }
        return noOfOps;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTests = in.nextInt();
        String[] strings = new String[numberOfTests];
        for (int i = 0; i < numberOfTests; ) {
            strings[i] = in.next();
            ++i;
        }
        for (int i = 0; i < numberOfTests; ) {
            System.out.println(getOperationCount(strings[i]));
            ++i;
        }
    }
}
