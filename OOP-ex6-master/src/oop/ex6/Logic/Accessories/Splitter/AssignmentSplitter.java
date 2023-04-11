package oop.ex6.Logic.Accessories.Splitter;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignmentSplitter extends LineSplitter {
    public AssignmentSplitter(String line) {
        super(line);
    }


    public ArrayList<HashMap<String, String>> split(String linetoSplit) {
        boolean assignment = true;
        String[] split = SplitBy(linetoSplit,Comma);
        ArrayList<String> rearranged = rearrange(split);
        removeEquals(split);
//        System.out.println(rearranged);
        return extractVars(rearranged,assignment);
    }

    public  ArrayList<HashMap<String,String>> extractVars(ArrayList<String> List, boolean Assignment) {
        ArrayList<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
        int definedVar = 0;
        int assigningVar = 1;
        for (String defineLine : List) {
            HashMap<String, String> map = new HashMap<String,String>();
            String[] split = defineLine.split(Space);
            map.put(Type, split[assigningVar]);
            map.put(DefinedVar, split[definedVar].replace(Equals, EmptyString));
        }
            return mapList;
    }
}
