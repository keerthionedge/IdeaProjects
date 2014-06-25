/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
package com.sub.sub;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    Note
    Not sure why my other ID keerthi.ramachandran@outlook.com is not working, resubmitting under new id
 */
public class Solution {
    private static final String NO_SUBSEGMENT_FOUND = "NO SUBSEGMENT FOUND";
    private static final String START_INDEX = "segmentStartIndex";
    private static final String END_POSITION_INDEX = "segmentEndPositionIndex";
    private static final String MULTI_ITEM_MAP = "multiItemMap";
    private static final String SINGLE_ITEM_MAP = "singleItemMap";
    private static final String VALIDATOR_MAP = "validatorMap";
    private static final String END_INDEX = "segmentEndIndex";

    /**
     * Regex go strip of all special characters
     *
     * @return problem string (Stripped of all special characters except space)
     */
    public static String getStrippedProblemString(String inProblemString) {
        return inProblemString.replaceAll("[^a-zA-Z\\s]+", "");
    }

    public static void solutionRunner(String inProblemString,
                                      int inNumberOfKeyWords,
                                      String[] inKeyWords) throws IOException {
        boolean keyFlag = validateIsAllKeyWordsPresent(inProblemString, inKeyWords);
        if (keyFlag) {
            int minimumPossibleLength = getMinimumPossibleLength(inKeyWords) - 1;
            Map<String, Object> map = getSegmentIndexToBeValidated(inProblemString, inKeyWords);
            Map<String, List<Integer>> multipleItemMap = (Map<String, List<Integer>>) map.get(MULTI_ITEM_MAP);
            Map<String, Integer> singleItemMap = (Map<String, Integer>) map.get(SINGLE_ITEM_MAP);
            Map<Integer, String> validatorMap = (Map<Integer, String>) map.get(VALIDATOR_MAP);
            if (multipleItemMap.keySet().size() == 0) {
                System.out.println(inProblemString.substring((Integer) map.get(START_INDEX), (Integer) map.get(END_POSITION_INDEX)));
            } else {
                int firstSingleItemPosition = Solution.getFirstSingleItemPosition(multipleItemMap, singleItemMap, validatorMap);
                int lastSingleItemPosition = Solution.getLastSingleItemPosition(multipleItemMap, singleItemMap, validatorMap);
                Map<String, Integer> startEndMap = Solution.getStartAndEnd(multipleItemMap,
                        singleItemMap,
                        validatorMap,
                        firstSingleItemPosition,
                        lastSingleItemPosition, minimumPossibleLength);
                System.out.println(inProblemString.substring(startEndMap.get(START_INDEX), startEndMap.get(END_INDEX)).trim());
            }
        } else {
            System.out.println(NO_SUBSEGMENT_FOUND);
        }
    }

    public static int getFirstSingleItemPosition(Map<String, List<Integer>> inMultiItemMap,
                                                 Map<String, Integer> inSingleItemMap,
                                                 Map<Integer, String> inValidatorMap) {
        int firstSingleItemPosition = 0;
        Set<Integer> integers = inValidatorMap.keySet();
        List<Integer> list = new ArrayList<Integer>(integers);
        Collections.sort(list);
        boolean breakCondition = true;
        while (breakCondition) {
            for (int i : list) {
                List<Integer> integerList = new ArrayList<Integer>();
                if (inSingleItemMap.containsKey(inValidatorMap.get(i))) {
                    firstSingleItemPosition = i;
                    breakCondition = false;
                    break;
                }
                if (inMultiItemMap.containsKey(inValidatorMap.get(i))) {
                    integerList = inMultiItemMap.get(inValidatorMap.get(i));
                    if (integerList.get(integerList.size() - 1) == i) {
                        firstSingleItemPosition = i;
                        breakCondition = false;
                        break;
                    }
                }
            }
            breakCondition = false;
        }
        return firstSingleItemPosition;
    }

    public static int getLastSingleItemPosition(Map<String, List<Integer>> inMultiItemMap,
                                                Map<String, Integer> inSingleItemMap,
                                                Map<Integer, String> inValidatorMap) {
        int lastSingleItemPosition = 0;
        Set<Integer> integers = inValidatorMap.keySet();
        List<Integer> list = new ArrayList<Integer>(integers);
        Collections.sort(list);
        Collections.reverse(list);
        boolean breakCondition = true;
        while (breakCondition) {
            for (int i : list) {
                List<Integer> integerList = new ArrayList<Integer>();
                if (inSingleItemMap.containsKey(inValidatorMap.get(i))) {
                    lastSingleItemPosition = i;
                    breakCondition = false;
                    break;
                }
                if (inMultiItemMap.containsKey(inValidatorMap.get(i))) {
                    integerList = inMultiItemMap.get(inValidatorMap.get(i));
                    if (integerList.get(0) == i) {
                        lastSingleItemPosition = i;
                        breakCondition = false;
                        break;
                    }
                }
            }
            breakCondition = false;
        }
        return lastSingleItemPosition;
    }

    public static Map<String, Integer> getStartAndEnd(Map<String, List<Integer>> inMultiItemMap,
                                                      Map<String, Integer> inSingleItemMap,
                                                      Map<Integer, String> inValidatorMap,
                                                      int inFirstSingleItemPosition,
                                                      int inLastSingleItemPosition,
                                                      int inMinimumPossibleLength) {
        Map<String, Integer> returnMap = new HashMap<String, Integer>(2);
        int startPosition = 0;
        int startIndex = 0;
        int endPosition = 0;
        int endIndex = 0;
        int tempEndIndex = 0;
        Boolean firstLoop = Boolean.TRUE;
        boolean isEndPositionReCalc4Next = true;
        Set<Integer> integers = inValidatorMap.keySet();
        List<Integer> list = new ArrayList<Integer>(integers);
        Collections.sort(list);
        for (int i : list) {
            if (i <= inFirstSingleItemPosition && firstLoop) {
                firstLoop = Boolean.FALSE;
                startPosition = i;
                startIndex = startPosition;
                endPosition = getLastSingleItemPosition(inMultiItemMap,
                        inSingleItemMap,
                        inValidatorMap);
                tempEndIndex = endPosition + inValidatorMap.get(endPosition).length() + 1;
                endIndex = tempEndIndex;
                if ((endIndex - startIndex) == inMinimumPossibleLength) {
                    returnMap.put(START_INDEX, startIndex);
                    returnMap.put(END_INDEX, endIndex);
                    return returnMap;
                }
                if (i < inFirstSingleItemPosition) { //being the last multiItem don't remove it
                    removeItemFromMultiItemMap(inMultiItemMap, inValidatorMap.get(i));
                }else if(i == inFirstSingleItemPosition){
                    inSingleItemMap.put(inValidatorMap.get(i),i);
                    inMultiItemMap.remove(inValidatorMap.get(i));
                }
            } else if (i <= inFirstSingleItemPosition) {
                startPosition = i;
                if (isEndPositionReCalc4Next) {
                    endPosition = getLastSingleItemPosition(inMultiItemMap,
                            inSingleItemMap,
                            inValidatorMap);
                }
                isEndPositionReCalc4Next = isRecomputeEndForNextIter(inMultiItemMap, inValidatorMap.get(i), endPosition, i);
                tempEndIndex = endPosition + inValidatorMap.get(endPosition).length() + 1;
                if (i < inFirstSingleItemPosition) {
                    removeItemFromMultiItemMap(inMultiItemMap, inValidatorMap.get(i));
                } else if(i == inFirstSingleItemPosition){
                    inSingleItemMap.put(inValidatorMap.get(i),i);
                    inMultiItemMap.remove(inValidatorMap.get(i));
                }
                if ((tempEndIndex - startPosition) < (endIndex - startIndex)) {
                    startIndex = startPosition;
                    endIndex = tempEndIndex;
                    if ((endIndex - startIndex) <= inMinimumPossibleLength) {
                        //put values in map & break
                        returnMap.put(START_INDEX, startIndex);
                        returnMap.put(END_INDEX, endIndex);
                        return returnMap;
                    }
                }
            } else {
                break;
            }
        }
        returnMap.put(START_INDEX, startIndex);
        returnMap.put(END_INDEX, endIndex);
        return returnMap;
    }

    private static boolean isRecomputeEndForNextIter(Map<String, List<Integer>> inMultiItemMap,
                                                     String inCurrentKey,
                                                     int inLastSingleItemPosition,
                                                     int currentPositionKey) {
        Boolean aBoolean = Boolean.TRUE;
        List<Integer> list = inMultiItemMap.get(inCurrentKey);
        if(list.get(0) < inLastSingleItemPosition && list.get(0)==currentPositionKey){
            if(list.size()>1 && list.get(1)<inLastSingleItemPosition){
                aBoolean = Boolean.FALSE;
            }
        }
        return aBoolean;
    }
    private static void removeItemFromMultiItemMap(Map<String, List<Integer>> inMultiItemMap, String s) {
        List<Integer> integerList = inMultiItemMap.get(s);
        List<Integer> subList = integerList.subList(1, integerList.size());
        inMultiItemMap.put(s, subList);
    }

    public static int getMinimumPossibleLength(String[] inKeyWords) {
        int i = 0;
        for (String s : inKeyWords) {
            i = i + 1 + s.length();
        }
        return i;
    }

    public static Map<String, Object> getSegmentIndexToBeValidated(String inProblemString, String[] inKeyWords) throws IOException {
        int segmentStartIndex = 0;
        boolean startSegmentComputed = false;
        int segmentEndPositionIndex = 0;
        int segmentEndIndex = 0;
        int currentIndex = 0;
        inProblemString = " " + inProblemString.toLowerCase() + " ";//to avoid failure in loop for first element
        Map<String, List<Integer>> multiItemMap = new LinkedHashMap<String, List<Integer>>();
        Map<String, Integer> singleItemMap = new LinkedHashMap<String, Integer>();
        Map<Integer, String> validatorMap = new LinkedHashMap<Integer, String>();
        for (String s : inKeyWords) {
            currentIndex = 0;
            List<Integer> index = new ArrayList<Integer>(1);
            while (currentIndex != -1) {
                currentIndex = inProblemString.indexOf(" " + s + " ", currentIndex);
                if (currentIndex != -1) {
                    if (currentIndex == 0) {
                        index.add(currentIndex);
                    } else {
                        index.add(currentIndex - 1); // -1 is compensation of the " " added to inProblemString
                    }
                    if (!startSegmentComputed || currentIndex < segmentStartIndex) {
                        startSegmentComputed = true;
                        if (currentIndex == 0) {
                            segmentStartIndex = currentIndex;
                        } else {
                            segmentStartIndex = currentIndex - 1; // -1 is compensation of the " " added to inProblemString
                        }
                    }
                    if (segmentEndPositionIndex == 0 || currentIndex + 1 + s.length() > (segmentEndPositionIndex)) {
                        if (currentIndex == 0) {
                            segmentEndPositionIndex = currentIndex;
                            segmentEndIndex = currentIndex;
                        } else {
                            segmentEndPositionIndex = currentIndex + s.length(); //bcz " " can be trimmed at output (else add -1)
                            segmentEndIndex = currentIndex - 1;// -1 is compensation of the " " added to inProblemString
                        }
                    }
                    ++currentIndex;
                }
            }
            if (index.size() > 1) {
                //Collections.sort(index);//to ensure the added items are sorted asc
                multiItemMap.put(s, index);
                for (Integer integer : index) {
                    validatorMap.put(integer, s);
                }
            } else if (index.size() == 1) {
                singleItemMap.put(s, index.get(0));
                validatorMap.put(index.get(0), s);
            }
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>(4);
        map.put(START_INDEX, segmentStartIndex);
        map.put(END_POSITION_INDEX, segmentEndPositionIndex);
        map.put(END_INDEX, segmentEndIndex);
        map.put(MULTI_ITEM_MAP, multiItemMap);
        map.put(SINGLE_ITEM_MAP, singleItemMap);
        map.put(VALIDATOR_MAP, validatorMap);
        return map;
    }

    private static boolean validateIsAllKeyWordsPresent(String inProblemString, String[] inKeyWords) {
        inProblemString = " " + inProblemString.toLowerCase() + " ";
        for (String s : inKeyWords) {
            if (inProblemString.indexOf(" " + s + " ") == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine().trim();
        line = getStrippedProblemString(line);
        int tempInt = Integer.parseInt(br.readLine().trim());
        Set<String> stringSet = new HashSet<String>(1);

        for (int i = 0; i < tempInt; ) {
            stringSet.add(br.readLine().trim());
            ++i;
        }
        String[] strings = stringSet.toArray(new String[stringSet.size()]);
        //Solution runner = new Solution(line,tempInt,strings);
        solutionRunner(line, tempInt, strings);
    }
}