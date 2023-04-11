package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Accessories.Splitter.DefinitionSplitter;
import oop.ex6.Logic.Accessories.VariableFactory;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodSignatureChecker extends Checker {

    public MethodSignatureChecker(ArrayList<Variable> LocalVars, ArrayList<Variable> GlobalVars, String lineToCheck
    , ArrayList<Method> methodsList, int depth) {
        super(LocalVars, GlobalVars, lineToCheck, depth, methodsList);
    }

    public ArrayList<Variable> Checkvar(String varName, String varType) throws Type1Exception {
        ArrayList<Variable> tempList = new ArrayList<>();
        Matcher matcher = getPattern().matcher(lineToCheck);
        return tempList;
    }

    @Override
    public ArrayList<Variable> Checkvar() throws Type1Exception, Type1Exception {
        return getVariables();
    }

    @Override
    protected Pattern getPattern() {
        return Pattern.compile(Brackets);
    }

    public ArrayList<Variable> getVariables(){
        int emptydef = 1;
        Matcher matcher = getPattern().matcher(lineToCheck);
        Pattern emptybracks = Pattern.compile("(\\( *?\\))");
        String defLine = cutString(matcher);
        Matcher emptymatcher = emptybracks.matcher(defLine);
        if(emptymatcher.matches()){
            return localVars;
        }
            return getVariablesFromDef(defLine,true);
        }

    protected ArrayList<Variable> getVariablesFromDef(String line, boolean isMethodSign) {
        int finalsign = 0;
        int minimumfordefinition = 2;
        int varType = 0;
        int finalvarType = 1;
        int varName = 1;
        int finalvarName = 2;
        int hasfinal = 3;
        boolean isFinal = false;
        boolean isGlobal = false;
        boolean initialized = false;
        ArrayList<HashMap<String, String>> varMap;
        line = line.replace("(", "");
        line = line.replace(")", "");
        varMap = splitVars(line);
        int finaldef = 3;
        int nonfinaldef = 2;
        ArrayList<Variable> varList = new ArrayList<>();
        VariableFactory factory = new VariableFactory();
        for (HashMap<String, String> map : varMap) {
            if (map.size() == nonfinaldef) {
                Variable newVar = factory.createVar(map.get(Type), map.get(DefinedVar), depth, initialized, isFinal, isGlobal);
                varList.add(newVar);
            }
            if (map.size() == finaldef) {
                isFinal = true;
                Variable newVar = factory.createVar(map.get(Type), map.get(DefinedVar), depth,
                        initialized, isFinal, isGlobal);
                varList.add(newVar);
            }
        }
//        String[] splitIntoLines = line.split(",");
//        if (splitIntoLines.length < minimumfordefinition){
//            return null;
//        }
//
//        if (isMethodSign){
//            for(String def : splitIntoLines){
//                String split[] = def.split("\\s+");
//                ArrayList<String> tempList = clean(split);
//                if (tempList.size() == hasfinal){
//                    isFinal = true;
//                }
//                if(isFinal){
//                    varList.add(factory.createVar(tempList.get(varType),tempList.get(varName),depth,initialized,isFinal,
//                            isGlobal));
//                }
//                else{ varList.add(factory.createVar(tempList.get(varType),tempList.get(varName),depth,initialized,
//                        isFinal,isGlobal));}
//            }
//        }
            return varList;
    }
    private ArrayList<HashMap<String,String>> splitVars(String line){
        DefinitionSplitter splitter = new DefinitionSplitter(line);
        return splitter.split(line);
    }
}
