package oop.ex6.Logic.Accessories;

import oop.ex6.Logic.Blocks.Block;
import oop.ex6.Logic.Blocks.WhileBlock;
import oop.ex6.Logic.Variables.Variable;

import java.util.ArrayList;
import java.util.HashMap;

public class FormerSplitter {
    ArrayList<String> Lines;
    public FormerSplitter(ArrayList<String> lines){
        Lines = lines;
    }

    public ArrayList<Block> SplitIntoBlocks(String string){
        WhileBlock block = new WhileBlock();
        block.setText(string);
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(block);
        return blocks;
    }

//    public int checkBlock(Block block){
//        ArrayList<String> linesToCheck = block.getText();
//        ArrayList<Variable> variables = block.getVariables();
//        if (variables.contains(null)){
//            return 1;
//        }
//    return 0;
//    }

    public HashMap<String,String> splitMultiples(String line){
        String[] SplitByCommas = line.split(",");
        HashMap<String,String> variables = new HashMap<String,String>();
        int Name = 0;
        int Value = 1;
        for (String definition : SplitByCommas) {
            String[] SplitByEquals = definition.split("=");
            variables.put(SplitByEquals[Name], SplitByEquals[Value]);
        }
        return variables;
    }
}
