package oop.ex6.Logic.Blocks;

import oop.ex6.Logic.Variables.Variable;
import oop.ex6.Logic.Variables.Variable;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Block {
    protected ArrayList<String> lines;
    protected ArrayList<Variable> variables;
    protected String text;
    protected Block parentBlock;
    protected ArrayList<Variable> Globalvariables;
    protected ArrayList<Variable> Finalvariables;
    protected String[] legal_variables = {"char","double","int","String","boolean"};
    /**
     *
     * @return : All variables used in this block.
     */
    protected abstract Block getBlockType();

//    public ArrayList<Variable> getVariables(){
//        for(String line:lines){
//            switch(line){
//                 case "int" :
//                Variable integer = new Integerr("a", getBlockType(), false);
//                if(variables.contains(integer)){
//                    variables.add(null);
//                    return variables;
//                }
//                variables.add(integer);
//            }
//        }
//    return variables;
//    }

    public void setText(String text){
        this.text = text;
    }

    public ArrayList<String> getText(){
        String[] Split = text.split("\n");
        ArrayList<String> newList = new ArrayList<>(Arrays.asList(Split));
    return newList;
    }


}