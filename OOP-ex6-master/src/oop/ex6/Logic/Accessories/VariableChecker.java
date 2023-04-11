package oop.ex6.Logic.Accessories;

import oop.ex6.Logic.LogicProblemException;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;

import java.util.ArrayList;
import java.util.HashMap;


public class VariableChecker {
    String[] myBlock;
    ArrayList<Variable> localVars;
    ArrayList<Variable> globalVars;
    HashMap<Variable,String> varMap;

    public VariableChecker(HashMap<Variable,String> variableMap,ArrayList<Variable> LocalVars,
                           ArrayList<Variable> GlobalVars){
        localVars = LocalVars;
        varMap = variableMap;
        globalVars = GlobalVars;
    }
    public void run(String varcandidate, int depth) throws Type1Exception {
    }

    public void checkUsage(String name, int depth) throws Type1Exception {
        int getOnly = 0;
        int noPriors = 0;
        int onlyone = 1;
        if (getVars(name).size() == noPriors) {
            throw new Type1Exception();
        }
        if (getVars(name).size() == onlyone) {
            if (!getVars(name).get(getOnly).getInitialization()) {
                throw new Type1Exception();
            }
        }
        VariableSorter sorter = new VariableSorter();
        localVars.sort(sorter);
        int lastind = localVars.size() - 1;
        if (!localVars.get(lastind).getInitialization()) {
            throw new Type1Exception();
        }
    }

    private ArrayList<Variable> getVars(String name){
        ArrayList<Variable> varsWithSameName = new ArrayList<>();
        for(Variable var:localVars){
            if (var.getName().equals(name)){
                varsWithSameName.add(var);
            }
        }
        return varsWithSameName;
    }

    public void checkAssignment(String name, int depth) throws Type1Exception{
        for(Variable var:getVars(name)){
            if (var.getName().equals(name)){
                if (var.getDepth() == depth){
                    throw new Type1Exception();
                }
            }
        }
    }

    private void checkDoubleAssignments(Variable vartoCheck) throws LogicProblemException {
        for(Variable var:globalVars){
            if(vartoCheck.getName().equals(var.getName())){
                throw new LogicProblemException();
            }
        }
    }
}