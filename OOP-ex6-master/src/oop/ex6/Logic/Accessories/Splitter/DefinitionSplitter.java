package oop.ex6.Logic.Accessories.Splitter;


import java.util.ArrayList;
import java.util.HashMap;

public class DefinitionSplitter extends LineSplitter {

    public DefinitionSplitter(String line) {
        super(line);
    }

    public ArrayList<HashMap<String, String>> split(String linetoSplit) {
        boolean assignment = false;
        if(linetoSplit.contains(Equals)){
            assignment = true;
        }

        String[] split = SplitBy(linetoSplit,Comma);
        ArrayList<String> rearranged = rearrange(split);
//        System.out.println(rearranged);
        return extractVars(rearranged,assignment);
    }

    protected ArrayList<HashMap<String,String>> extractVars(ArrayList<String> List, boolean Assignment){
        ArrayList<HashMap<String,String>> mapList = new ArrayList<HashMap<String,String>>();
        ArrayList<String> newList = new ArrayList<>();
        int typeind = 0;
        int definedVar = 1;
        for(String defineLine : List){
            HashMap<String, String> map = new HashMap<String,String>();
            String[] split = defineLine.split(Space);
            newList = removeEquals(split);
            map.put(Type,newList.get(typeind));
            map.put(DefinedVar,newList.get(definedVar));
            if(Assignment){
                int assigningVar = 2;
                map.put(AssigningVar,newList.get(assigningVar));
            }
            mapList.add(map);
        }
        return mapList;
    }
}
