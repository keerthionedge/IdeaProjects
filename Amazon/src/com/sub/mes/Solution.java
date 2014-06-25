package com.sub.mes;
/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/*
    Note
    Not sure why my other ID keerthi.ramachandran@outlook.com is not working, resubmitting under new id
 */
public class Solution {
    private static final int HHFormatInt = 1440;
    private int numberOfPeople;
    private int numberOfMinutes;

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int inNumberOfPeople) {
        numberOfPeople = inNumberOfPeople;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(int inNumberOfMinutes) {
        numberOfMinutes = inNumberOfMinutes;
    }

    Map<Integer,TimeSlot> timeSlotMap = new LinkedHashMap<Integer, Solution.TimeSlot>();

    public Map<Integer, TimeSlot> getTimeSlotMap() {
        return timeSlotMap;
    }

    public void setTimeSlotMap(Map<Integer, TimeSlot> inTimeSlotMap) {
        timeSlotMap = inTimeSlotMap;
    }

    public Solution() {
    }

    public static int convertTo24H(int inStart) {
        if(inStart == HHFormatInt)
            return 0;
        else
            return inStart% HHFormatInt;
    }

    public static Map<Integer,Integer> timeSlotMap(int inNumberOfPeople, int inNumberOfMinutes, Map<Integer,TimeSlot> inSlotMap){
        Map<Integer,Boolean> slotMap = getMapWithStatus(inSlotMap,inNumberOfMinutes);
        Map<Integer,Integer> SlotMap = getSlotMap(slotMap, inNumberOfMinutes);
        return SlotMap;
    }

    public static Map<Integer, Integer> getSlotMap(Map<Integer, Boolean> inSlotMap, int inInterval) {
        Set<Integer> integerSet = inSlotMap.keySet();
        Map<Integer, Integer> slotMap = new LinkedHashMap<java.lang.Integer, java.lang.Integer>();
        Integer startTime = 0;
        Integer endTime = 0;
        Boolean previousValid = Boolean.FALSE;
        Boolean iterateMore = Boolean.TRUE;
        for (Integer integer : integerSet) {
            if (iterateMore) {
                if (inSlotMap.get(integer)) {
                    if (!previousValid) {
                        startTime = integer;
                        previousValid = Boolean.TRUE;
                    }
                    endTime = integer + inInterval;
                    if (endTime == HHFormatInt) {
                        slotMap.put(startTime, 0);
                    }
                } else if (!(startTime == endTime)) {
                    previousValid = Boolean.FALSE;
                    slotMap.put(startTime, endTime);
                } else {
                    previousValid = Boolean.FALSE;
                }
            }
        }
        return slotMap;
    }

    public static Map<Integer, Boolean> getMapWithStatus(Map<Integer, TimeSlot> inSlotMap, int inInterval) {
        Boolean[] booleans = getBooleanIntervelled();
        Set<Integer> integers = inSlotMap.keySet();
        for(Integer integer: integers){
            TimeSlot timeSlot = inSlotMap.get(integer);
            int startTime = timeSlot.getStart();
            int endTime = timeSlot.getEnd();
            if (startTime < endTime) {
                for (int i = startTime; i < endTime; ) {
                    booleans[i] = Boolean.FALSE;
                    ++i;
                }
            } else if(endTime == 0){
                for(int i = startTime; i<=1439;){
                    booleans[i] = Boolean.FALSE;
                    ++i;
                }
            } else if(startTime > endTime) {
                for(int i=0; i<endTime;){
                    booleans[i] = Boolean.FALSE;
                    ++i;
                }
                for(int i = startTime; i<=1439;){
                    booleans[i] = Boolean.FALSE;
                    ++i;
                }
            }
        }
        final Boolean[] availbleSlots = booleans;
        Map<Integer, Boolean> booleanMap = getMapWithIntervals();
        Set<Integer> integerSet = booleanMap.keySet();
        for(Integer integer1:integerSet){
            if(isSlotValid(integer1,availbleSlots,inInterval)){
                booleanMap.put(integer1,Boolean.TRUE);
            }
            else
                booleanMap.put(integer1,Boolean.FALSE);
        }

        return booleanMap;
        /*Map<Integer, Boolean> booleanMap = getMapWithIntervals(inInterval);
        Set<Integer> integers = inSlotMap.keySet();
        for(Integer integer: integers){


        }*/
    }

    public static boolean isSlotValid(Integer inStart, Boolean[] inAvailbleSlots, int inInterval) {
        if(inStart == HHFormatInt)
            inStart = 0; // to avoid array out of bounds

        if(!inAvailbleSlots[inStart]){
            return Boolean.FALSE;
        } else if(!inAvailbleSlots[(inStart+inInterval-1)%HHFormatInt]){
            return Boolean.FALSE;
        }
        for(int i = inStart; i<(inInterval+inStart);){
            if(!inAvailbleSlots[i%HHFormatInt]){
                return Boolean.FALSE;
            }
            ++i;
        }
        return Boolean.TRUE;
    }

    public static Boolean[] getBooleanIntervelled() {
        Boolean[] array = new Boolean[HHFormatInt];
        Arrays.fill(array, Boolean.TRUE);
        return array;
    }

    /**
     * Create an static Map with Boolean Value as True for every minute
     */
    public static Map<Integer, Boolean> getMapWithIntervals() {
        Map<Integer, Boolean> booleanMap = new LinkedHashMap<Integer, java.lang.Boolean>();
        for(int i =0; i<HHFormatInt;){
            booleanMap.put(i,Boolean.TRUE);
            i++;
        }
        return booleanMap;
    }
    public static Map<Integer, Boolean> getMapWithIntervals(int inInterval) {
        Map<Integer, Boolean> booleanMap = new LinkedHashMap<Integer, java.lang.Boolean>();
        for(int i =0; i<HHFormatInt;){
            booleanMap.put(i,Boolean.FALSE);
            i+=inInterval;
        }
        return booleanMap;
    }

    public static class TimeSlot {
        private int HH_start;
        private int HH_end;
        private int MM_start;
        private int MM_end;
        //to make sure all computations are done in minute, rather than HH MM
        private int start;
        private int end;

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public TimeSlot(int inHH_start,  int inMM_start, int inHH_end, int inMM_end) {
            HH_start = inHH_start;
            HH_end = inHH_end;
            MM_start = inMM_start;
            MM_end = inMM_end;
            start = inHH_start*60 + inMM_start;
            if(start >= HHFormatInt){
                start = convertTo24H(start);
            }
            end = inHH_end*60 + inMM_end;
        }



        public int getHH_start() {
            return HH_start;
        }

        public void setHH_start(int inHH_start) {
            HH_start = inHH_start;
        }

        public int getHH_end() {
            return HH_end;
        }

        public void setHH_end(int inHH_end) {
            HH_end = inHH_end;
        }

        public int getMM_start() {
            return MM_start;
        }

        public void setMM_start(int inMM_start) {
            MM_start = inMM_start;
        }

        public int getMM_end() {
            return MM_end;
        }

        public void setMM_end(int inMM_end) {
            MM_end = inMM_end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof TimeSlot)) {
                return false;
            }

            TimeSlot timeSlot = (TimeSlot) o;

            if (HH_end != timeSlot.HH_end) {
                return false;
            }
            if (HH_start != timeSlot.HH_start) {
                return false;
            }
            if (MM_end != timeSlot.MM_end) {
                return false;
            }
            if (MM_start != timeSlot.MM_start) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = HH_start;
            result = 31 * result + HH_end;
            result = 31 * result + MM_start;
            result = 31 * result + MM_end;
            return result;
        }
    }
    public static Integer[] getProblemStringAsArray(String inProblemString) {
        Pattern pattern = Pattern.compile("\\s");
        String [] strings = pattern.split(inProblemString);
        List integers = new ArrayList<Integer>();
        for(String s:strings){
            integers.add(Integer.parseInt(s));
        }
        return (Integer[])integers.toArray(new Integer[integers.size()]);
    }

    public static String getFormattedString(Integer inInteger){
        if (inInteger == 0 || inInteger >= 1440) {
            return "00 00";
        } else {
            int part1 = inInteger/60;
            int part2 = inInteger%60;
            String string1;
            String string2;
            if(part1<=9){
                string1 = "0"+String.valueOf(part1);
            }else{
                string1 = String.valueOf(part1);
            }
            if(part2<=9){
                string2 = "0"+String.valueOf(part2);
            }else{
                string2 = String.valueOf(part2);
            }
            return string1+" "+string2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Integer[] integers = getProblemStringAsArray(line);
        Solution solution = new Solution();
        solution.setNumberOfMinutes(integers[1]);
        solution.setNumberOfPeople(integers[0]);
        Map<Integer,TimeSlot> slotMap = new LinkedHashMap<Integer, TimeSlot>();
        for(int i=1; i<=integers[0];){
            String in = br.readLine();
            Integer[] integers1 = getProblemStringAsArray(in);
            slotMap.put(i,new TimeSlot(integers1[0],integers1[1],integers1[2],integers1[3]));
            ++i;
        }
        solution.setTimeSlotMap(slotMap);
        Map<Integer,Integer> timeSlotMap = timeSlotMap(solution.getNumberOfPeople(), solution.getNumberOfMinutes(), solution.getTimeSlotMap());
        Set<Integer> integerSet = timeSlotMap.keySet();
        for(Integer integer:integerSet){
            if(!(integer+solution.getNumberOfMinutes()>1440))
                System.out.println(getFormattedString(integer)+" "+getFormattedString(timeSlotMap.get(integer)));
        }
    }
}