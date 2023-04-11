package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Accessories.Splitter.BracketsSplitter;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodChecker extends Checker {
    public MethodChecker(ArrayList<Variable> LocalVars,ArrayList<Variable> GlobalVars, String lineToCheck
            , int depth, ArrayList<Method> methodList) {
        super(LocalVars, GlobalVars, lineToCheck, depth, methodList);
    }

    @Override
    public ArrayList<Variable> Checkvar() throws Type1Exception{
        String methodName = getMethodName();
        Method originalMethod;
        boolean existenceFlag = true;
        int NumberOfMethods = methodList.size()-1;
        int counter = 0;
        int size = 0;
        for(Method method:methodList){
            if (method.getName().equals(methodName)){
                size = method.getVariables().size();
                break;
            }
            if(counter == NumberOfMethods){
                throw new Type1Exception();
            }
        }
        ArrayList<Variable> varList = new ArrayList<>();
        Matcher matcher = getPattern().matcher(lineToCheck);
        String line = cutString(matcher);
        BracketsSplitter splitter = new BracketsSplitter(line);
        ArrayList<String> allVars = splitter.splitAssignment(line);
        if(allVars.size() != size){
            throw new Type1Exception();
        }

        return localVars;
    }

    public ArrayList<Variable> Checkvar(String varName, String varType) throws Type1Exception {
        ArrayList<Variable> tempList = new ArrayList<>();
        return tempList;
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile(Brackets);
    }
    private void checkVariableList(ArrayList<String> currentVars,ArrayList<Variable> methodVars) throws Type1Exception {
        String varToCheck;
        String varToCheckType;
        for(int i=0;i<methodVars.size()-1;i++){
            String orgVarType = methodVars.get(i).getType();
            varToCheck = currentVars.get(i);
            varToCheckType = getVarType(varToCheck);
            if(!varToCheckType.equals(orgVarType)){
                throw new Type1Exception();
            }
        }
    }
}
