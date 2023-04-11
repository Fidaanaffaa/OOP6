package oop.ex6.Logic.Accessories;

import oop.ex6.Logic.Variables.Variable;

import java.util.Comparator;

public class VariableSorter implements Comparator<Variable> {
    public int compare(Variable o1, Variable o2){
        return o1.getDepth()-o2.getDepth();
    }
}
