package oop.ex6.Logic.Accessories.Splitter;

import java.util.ArrayList;
import java.util.HashMap;

public class FinalDefintitionSplitter extends LineSplitter {
    public FinalDefintitionSplitter(String line) {
        super(line);
    }


    public ArrayList<HashMap<String, String>> split(String linetoSplit) {
        boolean assignment = false;
        if(linetoSplit.contains(Equals)){
            assignment = true;
        }

        String[] split = SplitBy(linetoSplit,Comma);
        ArrayList<String> rearranged = rearrange(split);
        removeEquals(split);
//        System.out.println(rearranged);
//        System.out.println(extractVars(rearranged,assignment));
        return extractVars(rearranged,assignment);
    }

    public ArrayList<HashMap<String,String>> extractVars(ArrayList<String> List, boolean Assignment){
        ArrayList<HashMap<String,String>> maplist = new ArrayList<HashMap<String,String>>();
        int finalind = 0;
        int typeind = 1;
        int definedVar = 2;
        for(String defineLine : List){
            HashMap<String, String> map = new HashMap<String,String>();
            String[] split = defineLine.split(Space);
            ArrayList<String> newList = removeEquals(split);
        map.put(Final,newList.get(finalind));
        map.put(Type,newList.get(typeind));
        map.put(DefinedVar,newList.get(definedVar).replace(Equals,EmptyString));
        maplist.add(map);
        if(Assignment){
            int assigningVar = 3;
            map.put(AssigningVar,newList.get(assigningVar).replace(Equals,EmptyString));
        }
        }
        return maplist;
    }
}
