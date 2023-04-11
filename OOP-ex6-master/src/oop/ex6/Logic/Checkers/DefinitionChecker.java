package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Accessories.Splitter.DefinitionSplitter;
import oop.ex6.Logic.Accessories.Splitter.FinalDefintitionSplitter;
import oop.ex6.Logic.Accessories.Splitter.LineSplitter;
import oop.ex6.Logic.Accessories.VariableFactory;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefinitionChecker extends Checker{
    public DefinitionChecker(ArrayList<Variable> LocalVars, ArrayList<Variable> GlobalVars, String lineToCheck
            , ArrayList<Method> methodsList, int depth) {
        super(LocalVars, GlobalVars, lineToCheck, depth, methodsList);
    }

    public boolean checkFinal(String var) {
        final String Final = "final";
        if (var.equals(Final)){
            return true;
        }
        return false;
    }

//    public ArrayList<Variable> Checkvar(String varName, String varType) throws Type1Exception {
//        ArrayList<Variable> tempList = new ArrayList<>();
//        int speculative = 0;
//        int defWord = 0;
//        boolean isfinal = false;
//        ArrayList<String> varsCandidates = Splitter(lineToCheck);
//        if(checkFinal(varsCandidates.get(speculative))){
//            varsCandidates.remove(speculative);
//            isfinal = true;
//        }
//
//        String[] split = lineToCheck.split(",");
//        ArrayList<ArrayList<String>> sectionsList = new ArrayList<>();
//
//        for(String defSection:split){
//            sectionsList.add(Splitter(defSection));
//        }
//
//        for(ArrayList<String> defSection : sectionsList){
//            checkSectionLegality(defSection);
//        }
//
//        String vartype = varsCandidates.get(defWord);
//        varsCandidates.remove(varType);
//        for(String var : varsCandidates){
//            if(!var.equals("=")){
//                VariableFactory factory = new VariableFactory();
//                Variable newVariable = factory.createVar(varType,var,depth,false,isfinal,false);
//                tempList.add(newVariable);
//            }
//        }
//
//        return tempList;
//    }

    private void checkSectionLegality(ArrayList<String> varsCandidates)throws Type1Exception{
        int definedVar = 0;
        int equalsSign = 1;
        int assignedVar = 2;
        for(String var: varsCandidates) {
            for(Variable varb : getVars(var)){
                if(varb.getDepth() == depth){
                    throw new Type1Exception();
                }
            }
        }
    }
//    private boolean CheckSections(String section){
//        String[] split = section
//    }


    @Override
    public ArrayList<Variable> Checkvar() throws Type1Exception{
        ArrayList<Variable> varsToAdd = new ArrayList<Variable>();
        Matcher matcher = getPattern().matcher(lineToCheck);
        String line = cutString(matcher);
        int check = 0;
        int numberofVarstoAdd;
        LineSplitter splitter;
        boolean isfinal = false;
        boolean isInit  = false;
        String[] quicheck = lineToCheck.split(Space);
        if(quicheck[check].contains(Final)){
            isfinal = true;
            splitter = new FinalDefintitionSplitter(line);
        }
        else {
            splitter = new DefinitionSplitter(line);
        }
        final int noAssignment = 2;
        final int AssignmentOrFinal = 3;
        final int FinalAndAssignment = 4;
        String varType;
        String varName;
        String assignedVarName;
        boolean isConstant = false;
        for(HashMap<String,String> varMap : splitter.split(line)){
            int size = varMap.size();
            switch(size){
                case noAssignment:
                    varType = varMap.get(Type);
                    varName = varMap.get(DefinedVar);
                    checkDef(varName);
                    varsToAdd = addVars(varMap, isInit, isfinal,isConstant);
                    break;
                case AssignmentOrFinal :
                    if(varMap.containsKey(Final)){
                        isfinal = true;
                        varType  = varMap.get(Type);
                        varName = varMap.get(DefinedVar);
                        checkDef(varName);
                        varsToAdd = addVars(varMap, isInit, isfinal,isConstant);
                        break;
                    }
                    else{     varType  = varMap.get(Type);
                        varName = varMap.get(DefinedVar);
                        isInit = true;
                        assignedVarName = varMap.get(AssigningVar);
                        checkDef(varName);
                        if(!checkForConstants(assignedVarName)) {
                            checkInit(assignedVarName);
                            compareTypes(varName,assignedVarName,varType,isInit);
                            varsToAdd = addVars(varMap, isInit, isfinal,isConstant);
                        }
                        isConstant = true;
                        varsToAdd = addVars(varMap, isInit, isfinal,isConstant);
                        break;
                    }
                        case FinalAndAssignment :
                            isfinal = true;
                            isInit = true;
                            varType  = varMap.get(Type);
                            varName = varMap.get(DefinedVar);
                            assignedVarName = varMap.get(AssigningVar);
                            checkDef(varName);
                            if(!checkForConstants(assignedVarName)) {
                                checkInit(assignedVarName);
                                compareTypes(varName, assignedVarName, varType, true);
                            }
                            isConstant = true;
                            varsToAdd = addVars(varMap, isInit, isfinal,isConstant );
                            break;
            }
        }
        return varsToAdd;
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile("(final|int|boolean|char|double|String).*?;");
    }
}
