package oop.ex6.Logic.Accessories.Splitter;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;

public class BracketsSplitter extends LineSplitter {
    public BracketsSplitter(String line) {
        super(line);
    }

    @Override
    public ArrayList<HashMap<String, String>> split(String linetoSplit) {
        return null;
    }

    @Override
    protected ArrayList<HashMap<String, String>> extractVars(ArrayList<String> List, boolean Assignment) {
        return null;
    }

    private String removeBrackets(String line){
        line = line.replace("(","");
        line = line .replace(")","");
        return line;
    }
    private ArrayList<String> removeUnwanted(ArrayList<String> lineList){
        ArrayList<String> onlyVars = new ArrayList<>();
     for(String line:lineList){
         if (!UselessElements().contains(line)){
             onlyVars.add(line);
         }
     }
     return onlyVars;
    }

    protected ArrayList<String> UselessElements() {
        ArrayList<String> irrelevant = new ArrayList<>();
        irrelevant.add("&&");
        irrelevant.add("||");
        irrelevant.add("(");
        irrelevant.add(")");
        irrelevant.add("");
        irrelevant.add(" ");
        return irrelevant;
    }
    public ArrayList<String> splitAssignment(String linetoSplit) {
        String myLine = removeBrackets(linetoSplit);
        String[] split = myLine.split(",");
        ArrayList<String> tempList= new ArrayList<>();
        for (String assignment : split) {
            String[] splitbySpace = assignment.split("\\s+");
            tempList.addAll(clean(splitbySpace));
        }
        tempList = removeUnwanted(tempList);
        return tempList;
    }

    protected ArrayList<String> extractVarsAssigned(ArrayList<String> List, boolean Assignment) {
        return null;
    }
    /**
     * Spllits for anything containing brackets like method calls and if/whiles
     */

}
