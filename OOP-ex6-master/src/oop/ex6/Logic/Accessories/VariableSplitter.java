package oop.ex6.Logic.Accessories;

import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Checkers.CheckFactory;
import oop.ex6.Logic.Checkers.Checker;
import oop.ex6.Logic.Variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex6.main.Type1Exception;

public class VariableSplitter {
    public VariableSplitter(ArrayList<Variable> globalVars, ArrayList<Variable> finalVars, ArrayList<Method> methodList){
        this.methodList = methodList;
        this.globalVars = globalVars;
        this.finalVars = finalVars;
    }
    int Value = 1;
    ArrayList<Method> methodList = new ArrayList<>();
    Pattern pattern = Pattern.compile("(int|boolean|Char|double|String).*?;");
    ArrayList<Variable> variableList = new ArrayList<Variable>();
    HashMap<Variable,String> varMap = new HashMap<>();
    ArrayList<Variable> globalVars = new ArrayList<Variable>();
    ArrayList<Variable> finalVars = new ArrayList<Variable>();
    boolean isglobal;
    boolean isfinal;

    /**
     *
     * @param block : The block we plan to extract the variables from.
     * @return : A map of each variable
     */

    public void splitMultiples(String[] block) throws Type1Exception {
        HashMap<String, String> VariableMap = new HashMap<>();
        /* depth is the "scope" basically*/
        int depth = 0;

        for (String line : block) {
            String newline = "";
            Matcher matcher = pattern.matcher(line);

            if (line.contains("{")) {
                depth++;
            }

            if (line.contains("}")) {
                ClearVariables(depth);
                depth--;
                ArrayList<Variable> tempList = new ArrayList<>();
                for(Variable var:variableList){
                    if(var.getDepth() <= depth){
                        tempList.add(var);
                    }

                }
                variableList = tempList;
            }

            CheckFactory factory = new CheckFactory(variableList, globalVars, methodList, line, depth);
            Checker checker = factory.createChecker(line);
            if(!(checker == null)){
                for(Variable var:checker.Checkvar()){
                    if(!variableList.contains(var)){
                        variableList.add(var);
                    }
                }
            }
        }
    }

    public ArrayList<String> GetVarsFromLine(String line, String type, int depth) throws Type1Exception {

        int FirstSplit = 0;

        boolean initialized = false;
        ArrayList<String> variables = new ArrayList<>();
        String[] SplitByCommas = line.split(",");

            for (String definition : SplitByCommas) {
                String[] SplitByEquals = definition.split("=");
                for (int i = 0; i < SplitByEquals.length; i += 2) {
                    String name = DeleteSemi(SplitByEquals[i]);
                    String value = null;

                    if(SplitByEquals.length > 1) {
                        value = DeleteSemi(SplitByEquals[i + 1]);
                    }

                    if (definition.contains(type)){
                        name = GetRidOfDefinition(name, type);
                    }

                    if (variables.contains(name)){
                        return null;
                    }

                    VariableChecker checker = new VariableChecker(varMap,variableList,variableList);
                    checker.checkAssignment(name,depth);
                    VariableFactory factory = new VariableFactory();
                    Variable variable = factory.createVar(type,name,depth,initialized,isglobal,isfinal);
                    variableList.add(variable);
                    varMap.put(variable,name);
                    variables.add(name);
                    variables.add(value);
                }
            }
        return variables;
    }

    public boolean CheckVarAssignments(String[] block){
        return false;
    }

    public ArrayList<Variable> getVarList(){
        return variableList;
    }

    private String GetRidOfSpaces(String string, String type){
//        String[] split = string.split(" ");
//        for(String substring : split){
//            if (!substring.equals(" ")){
//                return substring;
//            }
//        }
        int first_letter = 0;
        int index = string.indexOf(type.charAt(0));
        return string.substring(index);
    }

    private String GetRidOfDefinition(String string, String type){
        string = GetRidOfSpaces(string,type);
        String [] split = string.split(" ");
        return split[Value];
    }

    private String DeleteSemi(String string){
        return string.replaceAll(";$","");
    }

     private void ClearVariables( int depth){
        ArrayList<Variable> newVars = new ArrayList<>();
        for (Variable var:variableList){
            if (var.getDepth() <= depth){
                newVars.add(var);
                varMap.remove(var);
            }
        }
        variableList = newVars;
//        System.out.println(varMap);
     }

     public void setGlobalVars(ArrayList<Variable> globalVars){
     this.globalVars = globalVars;
     }

     public ArrayList<Variable> getVariableList(){
        return variableList;
     }
}
