package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Accessories.VariableFactory;
import oop.ex6.Logic.Accessories.VariableSorter;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Checker {
    /**
     * Describes the index of the variable in definitions (excluding the equals sign)
     */
    protected int variable = 0;
    /**
     * Describes the index of the value in definitions (excluding the equals sign)
     */
    protected int value = 1;
    /**
     * some block
     */
    String[] myBlock;
    /**
     * Describes the local variables existing in the same block
     */
    protected ArrayList<Variable> localVars;
    /**
     * Describes the global variables
     */
    protected ArrayList<Variable> globalVars;
    /**
     * The line that we check
     */
    protected String lineToCheck;
    /**
     * Describes how "deep", or what scope we're in
     */
    protected int depth;
    /**
     * This is the pattern we use to find the function name
     */
    final String FuncName = "[a-zA-Z_]* ?\\(";
    /**
     * This is the pattern we use to find anything between brackets
     */
    ArrayList<Method> methodList;
    final String Brackets = "\\(.*?\\)";
    final String Type = "type";
    final String Final = "final";
    final String DefinedVar = "defined";
    final String AssigningVar = "assigning";
    final String EmptyString = "";
    final String Comma = ",";
    final String Space = "\\s+";
    final String Equals = "=";
    final String Int = "int";
    final String Boolean = "boolean";
    final String Char = "char";
    final String Stringvar = "String";
    final String Double = "double";

    public Checker(ArrayList<Variable> LocalVars, ArrayList<Variable> GlobalVars, String lineToCheck, int depth,
                   ArrayList<Method> methodList) {
        localVars = LocalVars;
        globalVars = GlobalVars;
        this.lineToCheck = lineToCheck;
        this.depth = depth;
        this.methodList = methodList;
    }

    protected String getVarType(String varName){
        for(Variable var:localVars){
            if(varName.equals(var.getName())){
                return var.getType();
            }
        }
        return null;
    }

    public ArrayList<Variable> addVars(HashMap<String,String> varcands, boolean isInit, boolean isfinal,
                                       boolean isConstant)
            throws Type1Exception {
        boolean isglobal = false;
        if (depth == 0){
            isglobal = true;
        }
        ArrayList<Variable> varstoAdd = new ArrayList<>();
        VariableFactory factory =  new VariableFactory();
        if(isInit){
            Variable varAssigned = factory.createVar(varcands.get(Type),varcands.get(DefinedVar),depth,isInit,isfinal
            , isglobal);
            if(!isConstant) {
                Variable varToAssign = factory.createVar(varcands.get(Type), varcands.get(AssigningVar), depth, isInit, isfinal
                        , isglobal);
                varstoAdd.add(varToAssign);
            }
            varstoAdd.add(varAssigned);
        }
        else {
            Variable varAssigned = factory.createVar(varcands.get(Type), varcands.get(DefinedVar), depth, isInit, isfinal
                    , isglobal);
            varstoAdd.add(varAssigned);
        }
        return varstoAdd;
    }

    public abstract ArrayList<Variable> Checkvar() throws Type1Exception, Type1Exception;

    protected ArrayList<String> Splitter(String string) {
        Matcher matcher = getPattern().matcher(string);
        ArrayList<String> vars = new ArrayList<>();
        if (matcher.find()) {
            int StartIndex = matcher.start();
            int LastIndex = matcher.end();
            String substring = string.substring(StartIndex, LastIndex);
            String[] split = substring.split(" ");
            for (String letter : split) {
                if (!UselessElements().contains(letter)) {
                    vars.add(letter);
                }
            }
        }
        return vars;
    }

    protected abstract Pattern getPattern();

    private void checkDoubleAssignments(Variable lineToCheck) throws Type1Exception {
        for (Variable var : globalVars) {
            if (lineToCheck.getName().equals(var.getName())) {
                throw new Type1Exception();
            }
        }
    }
    public void compareTypes(String defVar, String asVar, String varType, boolean isDef) throws Type1Exception{
        if (isDef) {
            int lastIndex = getVars(asVar).size()-1;
            ArrayList<Variable> asVarList = variableSorter(getVars(asVar));
            if(!asVarList.get(lastIndex).getType().equals(varType)){
                throw new Type1Exception();
            }
        }
    }

    public void checkInit(String varName) throws Type1Exception{
        int empty = 0;
        if (getVars(varName).size() == empty ){
            throw new Type1Exception();
        }
        ArrayList<Variable> varsToCompare = variableSorter(getVars(varName));
        int finalIndex = varsToCompare.size()-1;
        if(!varsToCompare.get(finalIndex).getInitialization()){
            throw new Type1Exception();
        }
    }

    public boolean checkForConstants(String line){
        Pattern pattern = Pattern.compile("((true|false|[0-9]|\")*?;|(true|false|[0-9]|\"))");
        Matcher matcher = pattern.matcher(line);
        boolean find = matcher.find();
        return find;
    }

    public void checkDef(String varName) throws Type1Exception {
        int empty = 0;
        int lastindex = getVars(varName).size()-1;
        if (getVars(varName).size() == empty) {
            return;
        }
        ArrayList<Variable> varList = variableSorter(getVars(varName));
        if(varList.get(lastindex).getInitialization()){
            throw new Type1Exception();
        }
    }

    public void checkUsage(String name) throws Type1Exception {

//        int getOnly = 0;
//        int noPriors = 0;
//        int onlyone = 1;
//        if (getVars(name).size() == noPriors) {
//            throw new Type1Exception();
//        }
//        if (getVars(name).size() == onlyone) {
//            if (!getVars(name).get(getOnly).getInitialization()) {
//                throw new Type1Exception();
//            }
//        }
//        /* Sorting all of the variables with the same name by depth*/
//        ArrayList<Variable> sortedVars = variableSorter(getVars(name));
//        int lastind = sortedVars.size() - 1;
//        if (!sortedVars.get(lastind).getInitialization()) {
//            throw new Type1Exception();
//        }
    }

    private void checkAssignment(String variable, String assignedValue) throws Type1Exception {
        ArrayList<Variable> sortedLocalVars = variableSorter(localVars);
        ArrayList<Variable> varValue = variableSorter(getVars(assignedValue));
        ArrayList<Variable> vartobeAssigned = variableSorter(getVars(variable));
        checkUsage(variable);
        checkUsage(assignedValue);
        int lastVariableAssignment = vartobeAssigned.size() - 1;
        int lastVariableValue = varValue.size() - 1;
        String varValueType = varValue.get(lastVariableValue).getType();
        String assignedValueType = vartobeAssigned.get(lastVariableAssignment).getType();
        /* Checking that both variables are of the same type*/
        if (!(varValueType.equals(assignedValue))) {
            throw new Type1Exception();
        }
    }

    protected ArrayList<Variable> getVars(String name) {
        ArrayList<Variable> varsWithSameName = new ArrayList<>();
        for (Variable var : localVars) {
            if (var.getName().equals(name)) {
                varsWithSameName.add(var);
            }
        }
        for(Variable var: globalVars){
            if(var.getName().equals(name)){
                varsWithSameName.add(var);
            }
        }
        return varsWithSameName;
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

    public ArrayList<Variable> variableSorter(ArrayList<Variable> varsToSort) {
        VariableSorter sorter = new VariableSorter();
        varsToSort.sort(sorter);
        return varsToSort;
    }

    public String getMethodName() {
        Pattern pattern = Pattern.compile(FuncName);
        Matcher matcher = pattern.matcher(lineToCheck);
        String name = cutString(matcher);
        name = name.replace("(", "");
        return name;
    }

    protected String cutString(Matcher matcher) {
        String string = "";
        if (matcher.find()) {
            int StartIndex = matcher.start();
            int LastIndex = matcher.end();
            string = lineToCheck.substring(StartIndex, LastIndex);
        }
        return string;
    }

    public static ArrayList<String> clean(String[] line){
        ArrayList<String> tempList = new ArrayList<>();
        for(String sater: line ){
            if(!sater.equals("")){
                sater = sater.replaceAll(" ","");
                tempList.add(sater);
            }
        }
        return tempList;
    }
}
