package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Accessories.Splitter.AssignmentSplitter;
import oop.ex6.Logic.Accessories.Splitter.BracketsSplitter;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfWhileChecker extends Checker {

    public IfWhileChecker(ArrayList<Variable> LocalVars, ArrayList<Variable> GlobalVars, String lineToCheck
            , ArrayList<Method> methodsList, int depth) {
        super(LocalVars, GlobalVars, lineToCheck, depth, methodsList);
    }

    public ArrayList<Variable> Checkvar() throws Type1Exception {
        ArrayList<Variable> varList = new ArrayList<>();
        Matcher matcher = getPattern().matcher(lineToCheck);
        String line = cutString(matcher);
        BracketsSplitter splitter = new BracketsSplitter(line);
        ArrayList<String> allVars = splitter.splitAssignment(line);
        for(String var:allVars){
            if(!checkForConstants(var)){
                checkInit(var);
                if (!(getVarType(var).equals(Boolean)) && (!getVarType(var).equals(Int))&&
                        (!getVarType(var).equals(Double))){
                    throw new Type1Exception();
                }
            }
            else { checkConstant(var);}
        }
        return localVars;
    }

    private boolean checkIfboolean(String var) throws Type1Exception{
        ArrayList<Variable> varsWithSameName = variableSorter(getVars(var));
        String Boolean = "boolean";
        String Double = "double";
        String Int = "int";
        int lastIndex = varsWithSameName.size()-1;
        if(varsWithSameName.get(lastIndex).getType().equals(Boolean)){
            return true;
        }
        if(varsWithSameName.get(lastIndex).getType().equals(Double)){
            return true;
        }
        if(varsWithSameName.get(lastIndex).getType().equals(Int)) {
            return true;
        }
        throw new Type1Exception();
    }

    private boolean checkConstant(String constant) throws Type1Exception{
        Pattern pattern = Pattern.compile("(true|false|[0-9])");
        Matcher matcher = pattern.matcher(constant);
        if(!matcher.find()){
            throw new Type1Exception();
        }
        return matcher.find();
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("\\(.*?\\)");
    }

//    protected ArrayList<String>  Splitter(String string) {
//        Pattern pattern = Pattern.compile("\\( .*? \\)");
//        Matcher matcher = pattern.matcher(string);
//        ArrayList<String> vars = new ArrayList<>();
//        while (matcher.find()) {
//            int StartIndex = matcher.start();
//            int LastIndex = matcher.end();
//            String substring = string.substring(StartIndex, LastIndex);
//            String[] split = substring.split(" ");
//            for (String letter : split) {
//                if (!UselessElements().contains(letter)) {
//                    vars.add(letter);
//                }
//            }
//        }
//        return vars;
//    }
}
