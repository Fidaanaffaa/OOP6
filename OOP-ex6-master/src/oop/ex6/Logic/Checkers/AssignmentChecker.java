package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Accessories.Splitter.AssignmentSplitter;
import oop.ex6.Logic.Accessories.VariableFactory;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssignmentChecker extends Checker {
    final boolean Initialized = true;
    boolean isfinal;
    boolean isglobal;
    public AssignmentChecker(ArrayList<Variable> LocalVars, ArrayList<Variable> GlobalVars, String lineToCheck
            , ArrayList<Method> methodsList, int depth) {
        super(LocalVars, GlobalVars, lineToCheck, depth,methodsList);
    }

    public ArrayList<Variable> Checkvar() throws Type1Exception {
        ArrayList<Variable> varList = new ArrayList<>();
        Matcher matcher = getPattern().matcher(lineToCheck);
        String line = cutString(matcher);
        boolean isConstant = false;
        AssignmentSplitter splitter = new AssignmentSplitter(line);
        ArrayList<HashMap<String,String>> varMapList = splitter.split(line);
        for(HashMap<String,String> varMap : varMapList) {
            String variableType = varMap.get(Type);
            String definedVarname =  varMap.get(DefinedVar);
            String assigningVarname = varMap.get(AssigningVar);
            isConstant = checkForConstants(assigningVarname);
            checkInit(definedVarname);
            if(!isConstant) {
                checkInit(assigningVarname);
            }
            varList = addVars(varMap,true,isfinal,isConstant);
        }
        return varList;
    }


    @Override
    protected Pattern getPattern() {
        return Pattern.compile("(int|boolean|char|double|String) *?;");
    }
}
