package oop.ex6.Logic.Accessories.Splitter;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class LineSplitter {
    String line;
    int lineNum;
    String Id;
    final String Type = "type";
    final String Final = "final";
    final String DefinedVar = "defined";
    final String AssigningVar = "assigning";
    final String EmptyString = "";
    final String Comma = ",";
    final String Space = "\\s+";
    final String Equals = "=";

    public LineSplitter(String line) {
        this.line = line;
    }
    public abstract ArrayList<HashMap<String, String>> split(String linetoSplit);

    protected abstract ArrayList<HashMap<String,String>> extractVars(ArrayList<String> List, boolean Assignment);

    protected String[] SplitBy(String line, String criteria) {
        return line.split(criteria);
    }

    protected ArrayList<String> clean(String[] line) {
        ArrayList<String> tempList = new ArrayList<>();
        for (String sater : line) {
            if (!sater.equals(EmptyString)) {
                sater = sater.replaceAll(Space, EmptyString);
                tempList.add(sater);
            }
        }
        return tempList;
    }

    public ArrayList<String> rearrange(String[] split) {
        ArrayList<String> rearrangedDefs = new ArrayList<>();
        for (String cline : split) {
            int counter = 0;
            int start = 0;
            String temp = "";
            String[] tempSplit = cline.split(" ");
            for (String aline : clean(tempSplit)) {
                if(counter == start){
                    temp = temp.concat(aline);
                }
                else{temp = temp.concat(" " + aline);}
                counter++;
            }
            rearrangedDefs.add(temp);
        }
        return rearrangedDefs;
    }
    public ArrayList<String> removeEquals(String[] list){
        ArrayList<String> listWithoutEquals = new ArrayList<String>();
        for(String line:list){
            if(!line.equals(Equals)){
                line = line.replace(Equals,EmptyString);
                listWithoutEquals.add(line);
            }
        }
        return listWithoutEquals;
    }
}
