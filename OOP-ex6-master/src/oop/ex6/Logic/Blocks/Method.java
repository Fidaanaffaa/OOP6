package oop.ex6.Logic.Blocks;

import oop.ex6.Logic.Variables.Variable;

import java.util.ArrayList;

public class Method {
    private String method_name;
    private ArrayList<Variable> variableList = new ArrayList<>();
    public Method(String name){
        method_name = name;
    };
    public String getName(){
        return method_name;
    }
    public ArrayList<Variable> getVariables(){ return variableList;}
    public void addVariables(ArrayList<Variable> varsToAdd){
        variableList.addAll(varsToAdd);
    }
}
