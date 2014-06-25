package com.sub.fib;
/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
/**
 * This class... todo (RK) javadoc
 *
 * @author <a href="mailto:keerthionedge@gmail.com">Keerthi Ramachandran</a>, 9/12/12
 * @since 2.4. /2012-09-12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
    Note
    Not sure why my other ID keerthi.ramachandran@outlook.com is not working, resubmitting under new id
 */
/**
 * Given a number K, find the smallest Fibonacci number that shares a common factor( other than 1 ) with it. A number is said to be a common factor of
 * two numbers if it exactly divides both of them.
 * Output two separate numbers, F and D, where F is the smallest fibonacci number and D is the smallest number other than 1 which divides K and F.
 * Input Format
 * First line of the input contains an integer T, the number of testcases. Then follows T lines, each containing an integer K.
 * Output Format
 * Output T lines, each containing the required answer for each corresponding testcase.
 * Sample Input
 * 3
 * 3
 * 5
 * 161
 * Sample Output
 * 3 3
 * 5 5
 * 21 7
 * Explanation
 * There are three testcases. The first test case is 3, the smallest required fibonacci number  3. The second testcase is 5 and the third is 161. For
 * 161 the smallest fibonacci numer sharing a common divisor with it is 21 and the smallest number other than 1 dividing 161 and 7 is 7.
 * Constraints :
 * 1 <= T <= 5 2 <= K <= 1000,000 The required fibonacci number is guranteed to be less than 10^18.
 */

public class Solution {
    /**
     *
     * @param inUpperLimit UpperLimit till which fibonocci series has to be filled out
     * @return List<BigInteger>
     */
    public static List<Long> populateFibonacci(long inUpperLimit) {
        List<Long> list = new ArrayList<Long>();
        if (inUpperLimit == 0) {
            list.add(0L);
            return list;
        } else if (inUpperLimit == 1) {
            list.add(0L);
            list.add(1L);
        } else {
            long Fn2 = 0l;
            long Fn1 = 1l;
            long Fn = Fn1 + Fn2;
            list.add(Fn2);
            list.add(Fn1);
            while (Fn <= inUpperLimit) {
                list.add(Fn);

                //Swap Values
                Fn2 = Fn1;
                Fn1 = Fn;
                Fn = Fn1 + Fn2;
            }
        }
        return list;
    }

    public static Integer[] listPrimes(int inLimit) {

        boolean[] primeIndex = new boolean[inLimit];
        Arrays.fill(primeIndex, true);
        primeIndex[0] = primeIndex[1] = false;
        for (int i = 2; i < primeIndex.length; i++) {
            if (primeIndex[i]) {
                for (int j = 2; i * j < primeIndex.length; j++) {
                    primeIndex[i * j] = false;
                }
            }
        }
        List<Integer> integerList = new ArrayList<Integer>(1);
        for (int i = 1; i < inLimit; i++) {
            if (primeIndex[i]) {
                integerList.add(i);
            }
        }
        return integerList.toArray(new Integer[integerList.size()]);
    }

    public static int getDivisor(Long inNumber,final Integer[] inPrimes) {
        return getDivisor(inNumber,inPrimes,0);
    }

    public static long getLowestPossibleFn(int inDivisor, Long inNumber, final List<Long> inFibonacciList){
        Long limit = inNumber;
        Long start =(long)inDivisor;
        int i = 1;
        while(start<=limit){
            if (inFibonacciList.contains(start)) {
                return  start;
            }
            ++i;
            start = (long)inDivisor*i;
        }
        return 0;
    }

    public static int getDivisor(Long inNumber, final Integer[] inPrimes, int inStart) {
        for (Integer integer : inPrimes) {
            if (integer > inStart) {
                if (inNumber % integer == 0) {
                    return integer;
                }
            }
        }
        return 0;
    }

    public static boolean[] primeValidator(int inLimit){
        boolean[] primeIndex = new boolean[inLimit+2];//+1 to avoid indexoutofbounds
        Arrays.fill(primeIndex, true);
        primeIndex[0] = primeIndex[1] = false;
        for (int i = 2; i < primeIndex.length; i++) {
            if (primeIndex[i]) {
                for (int j = 2; i * j < primeIndex.length; j++) {
                    primeIndex[i * j] = false;
                }
            }
        }
        return primeIndex;
    }

    public static Integer[] listPrimes(int inLimit,boolean[] inPrimeIndex) {
        boolean[] primeIndex = inPrimeIndex;
        List<Integer> integerList = new ArrayList<Integer>(1);
        for (int i = 0; i <= inLimit+1; i++) {
            if (primeIndex[i]) {
                integerList.add(i);
            }
        }
        return integerList.toArray(new Integer[integerList.size()]);
    }

    public static long getLowestPossibleFn(int inDivisor,  final List<Long> inFibonacciList){
        Long returnValue = 0l;
        for(Long aLong:inFibonacciList){
            if(aLong != 0l && aLong%inDivisor == 0){
                return aLong;
            }
        }
        return returnValue;
    }

    public static void computeAndPrint(Integer[] inInput) throws IOException{
        //sorted array causes issue, so use unsorted array & copy for other purpose
        Integer[] integers = Arrays.copyOf(inInput,inInput.length);
        Arrays.sort(integers);
        final int lastIndex = inInput.length-1;
        final List<Long> fibonacciList = populateFibonacci((long)Math.pow((double)10,(double)18));
        boolean[] primesIndex = primeValidator(integers[lastIndex]);
        Integer[] primesList = listPrimes(integers[lastIndex],primesIndex);
        int divisor = 0;
        long lowestPossibleFn = 0L;
        for (int i : inInput) {
            if ((i <= 1) || (i > 1000001)) {
                throw new IOException();
            }
            divisor = getDivisor((long) i, primesList, divisor, primesIndex);
            lowestPossibleFn = getLowestPossibleFn(divisor, fibonacciList);
            while (divisor == 0 || lowestPossibleFn == 0) {
                divisor = getDivisor((long) i, primesList, divisor, primesIndex);
                lowestPossibleFn = getLowestPossibleFn(divisor, fibonacciList);
            }
            System.out.println(lowestPossibleFn + " " + divisor);
            divisor = 0;
            lowestPossibleFn = 0L;
        }
    }

    public static int getDivisor(long inI, Integer[] inPrimesList, int divisor, boolean[] inPrimesIndex) {
        if (inI > divisor && inPrimesIndex[(int) inI]) {
            return (int) inI;
        } else {
            return getDivisor(inI, inPrimesList, divisor);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int numberOfFib = Integer.parseInt(line);
        if(numberOfFib<=0 || numberOfFib>5){
            throw new IOException();
        }
        List<Integer> integers = new ArrayList<Integer>();
        for(int i=0; i<numberOfFib;){
            int localint = Integer.parseInt(br.readLine());
            if(localint<=1 || localint>1000000){
                throw new IOException();
            }
            integers.add(localint);

            ++i;
        }
        Integer[] ints = integers.toArray(new Integer[integers.size()]);
        computeAndPrint(ints);
    }


}