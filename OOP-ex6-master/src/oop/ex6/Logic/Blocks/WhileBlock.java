package oop.ex6.Logic.Blocks;

public class WhileBlock extends Block {
    public static WhileBlock getInstance(){
        return new WhileBlock();
    }

    protected Block getBlockType(){
        return new WhileBlock();
    }
}
