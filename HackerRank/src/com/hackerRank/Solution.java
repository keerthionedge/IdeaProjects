package com.hackerRank;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) throws IOException {
        /*
                2 <= numberOfTests <= 105
                1 <= elements of the array <= 10^18
                2 <= Sum of numberOfTests over all test cases <= 100000
         */
        Scanner in = new Scanner(System.in);
        long numberOfTests;
        numberOfTests = in.nextLong();
        //structure for storing the input
        Map<Long, List<Long>> listMap = new HashMap<Long, List<Long>>();
        long numberOfRemainingTests = numberOfTests;
        while (numberOfRemainingTests >= 1) {
            long numberOfEntry = in.nextLong();
            long entryCount = numberOfEntry;
            List<Long> longList = new ArrayList<Long>();
            while (numberOfEntry >= 1) {
                longList.add(in.nextLong());
                --numberOfEntry;
            }

            if (entryCount != longList.size()) {
                throw new IOException("read size don't match");
            }
            listMap.put(numberOfRemainingTests, longList);
            --numberOfRemainingTests;
        }
        computeSolution(numberOfTests, listMap);
    }

    public static List<Long> getNumbersFromLine(long inNumberOfEntry, String longString) throws IOException {
        List<Long> longList = new ArrayList<Long>();
        Pattern pattern = Pattern.compile("\\s");
        for (String s : pattern.split(longString)) {
            longList.add(Long.decode(s));
        }
        if (inNumberOfEntry != longList.size()) {
            throw new IOException();
        }
        return longList;
    }

    /**
     * <p>print the solution</p>
     *
     * @param inNumberOfTests numberOfTests
     * @param integerListMap  input in required format
     */
    public static void computeSolution(long inNumberOfTests, Map<Long, List<Long>> integerListMap) {
        for (Long keys : integerListMap.keySet()) {
            System.out.println(getAndMinimum(integerListMap.get(keys)));

        }
    }

    /**
     * <p> & all the elements, which is the minimum :-P i'm wrong, it could be anything</p>
     *
     * @param integers
     * @return
     */
    public static long getAndMinimum(List<Long> integers) {
        if (integers.size() == 0 || integers.size() == 1) {
            return 0;
        } else {
            /*
                1 2 3 4
                1
                        1 2     1 3     1 4
                        1 2 3   1 3 4
                        1 2 3 4
                2
                        2 3     2 4
                        2 3 4
                3
                        3 4

                        i.e., powerset minus the empty & one element set

                        if it's '0' at any point, then exit the loop (can it be negative ??)

                        use recursive method call to do it
                        1 2 3 4

                4       1 2 3 4
                        2 3 4
                        3 4
                        2 4
                        1 4
                3       1 2 3
                        2 3
                        1 3
                2       1 2

                1
                        1   2
                            2 3
                            2 3 4
                            3
                            3 4
                            4
                2       2   3
                            3 4
                            4
                3       3   4

             */
            Long minimum = null;
            for (int i = 0; i < (integers.size() - 1); ) {                  //pos 0
                for (int j = i + 1; j < (integers.size()); ) {               //pos 1
                    Long computedResult = integers.get(i) & integers.get(j);
                    for (int k = j; k < (integers.size()); ) {              //pos 1,2,3
                        //System.out.println("        line    :" + i + "  " + j + "   " + k);
                        if (computedResult!=null &&computedResult == 0) {
                            break;
                        }
                        if (j == k) {
                            computedResult = integers.get(i) & integers.get(j);
                            ////System.out.println(integers.get(i) + "\t" + integers.get(j));
                        } else {
                            computedResult = computedResult & integers.get(k);
                            ////System.out.println("\t\t"+integers.get(k));
                        }
                        if (minimum == null) {
                          //  System.out.println(computedResult);
                            minimum = computedResult;
                        } else {
                            //System.out.println(computedResult);
                            if (computedResult <= minimum)
                                minimum = computedResult;
                        }
                        ++k;
                    }
                    ++j;
                }
                ++i;
            }
            Long resultant = null;
            for (Long integer : integers) {
                if (resultant != null) {
                    resultant = resultant & integer; //bitwise AND
                } else {
                    resultant = integer;
                }
            }
            return resultant;
        }

    }

    private static Long getCombination(Long aLong, List<Long> longs) {
        Long resultant = aLong;
        //System.out.print(aLong);
        for (Long aLong1 : longs) {
            //System.out.print("  " + aLong1);
        }
        //System.out.println();
        for (Long integer : longs) {
            resultant = resultant & integer; //bitwise AND
        }
        return resultant;
    }
}
