package com.sub.cos;
/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
    Note
    Not sure why my other ID keerthi.ramachandran@outlook.com is not working, resubmitting under new id
 */
public class Solution {

    private static final String B = "b";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Map<Integer,int[][]> integerMap =new LinkedHashMap<Integer, int[][]>();
        int T = Integer.parseInt(line);
        int i = 0;
        while(i<T){
            int L = Integer.parseInt(br.readLine());
            int[][] arr =new int[L][L];
            for(int j=0;j<L;) {
                String s= br.readLine();
                getLineIntoArray(s,L,j,arr);
                ++j;
            }
            integerMap.put(i,arr);
            ++i;
        }
        getNumberOfConnectedSets(integerMap);
        //getBooleanMapWithPosition(integerMap);
    }

    public static Map<Integer,Set<String>> getBooleanMapWithPosition(Map<Integer, int[][]> inIntegerMap) {
        Set<Integer> keys = inIntegerMap.keySet();
        Map<Integer,Set<String>> validatorMap = new LinkedHashMap<Integer, Set<String>>();
        for(int i:keys){
            validatorMap.put(i,getValidPositionStrings(inIntegerMap.get(i)));
        }
        return validatorMap;
    }

    public static Set<String> getValidPositionStrings(int[][] inInts) {
        int loopCount = inInts.length;
        Set<String> stringSet = new LinkedHashSet<String>();
        for(int i=0;i<loopCount;){
            for(int j =0; j<loopCount;){
                if(inInts[i][j] == 1){
                    stringSet.add(i+ B +j);
                }
                ++j;
            }
            ++i;
        }
        return stringSet;
    }

    /**
     *  -1-1    0-1      +1-1
     *  -1 0    inB      +1 0
     *  -1+1    0+1      +1+1
     * @param inStrings
     * @param inB
     * @return
     */
    public static Set<String> getNeighbours(Set<String> inStrings,String inB){
        String[] XY = inB.split(B);
        int xPos = Integer.parseInt(XY[0]);
        int yPos = Integer.parseInt(XY[1]);
        Set<String> stringSet = new LinkedHashSet<String>();
        String[] keys = {
                (xPos - 1) + B + (yPos - 1),
                (xPos) + B + (yPos - 1),
                (xPos + 1) + B + (yPos - 1),
                (xPos - 1) + B + (yPos),
                (xPos + 1) + B + (yPos),
                (xPos - 1) + B + (yPos + 1),
                (xPos) + B + (yPos + 1),
                (xPos + 1) + B + (yPos + 1)};
        for(String s: keys){
            if(inStrings.contains(s)){
                stringSet.add(s);
            }
        }
        return stringSet;
    }

    /**
     * call this method, it'll strip off the connected first set, & return the remaining one
     * @param inStrings
     * @return
     */
    public static Set<String> getAllNeighbours(Set<String> inStrings){
        String string = inStrings.iterator().next();
        inStrings.remove(string);
        Set<String> stringSet = new LinkedHashSet<String>();
        stringSet.add(string);
        Queue<String> queue = new ArrayDeque<String>();
        queue.add(string);
        while(!queue.isEmpty() && !inStrings.isEmpty()){    //don't bother running the loop when the main collection is empty
            Set<String> set = getNeighbours(inStrings,queue.poll()); //get the neighbours&remove the current element from queue
            queue.addAll(set);//add the neighbours to the queue
            inStrings.removeAll(set);//remove it from the main collection
        }
        return inStrings;
    }

    public static void getNumberOfConnectedSets(Map<Integer, int[][]> inIntegerMap) {
        Map<Integer, Set<String>> map = getBooleanMapWithPosition(inIntegerMap);
        Set<Integer> set = map.keySet();
        for (int i : set) {
            int result = getConnectedSetsCount(map.get(i));
            System.out.println(result);
        }
    }

    public static int getConnectedSetsCount(Set<String> inStrings) {
        int i = 0;
        while (inStrings.size() != 0) {
            getAllNeighbours(inStrings);
            ++i;
        }
        return i;
    }

    private static void getLineIntoArray(String s, int l, int j, int[][] arr) {
        String[] strings = s.split("\\s");
        int i =0;
        for(String s1:strings){
            arr[j][i] = Integer.parseInt(s1);
            ++i;
        }
    }

}
