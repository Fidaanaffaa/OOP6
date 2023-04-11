package oop.ex6.Logic.Blocks;

public class MethodBlock extends Block {
    private Method method;
    public MethodBlock(Method assignedMethod){
        method = assignedMethod;
    }
    public Method getMethod(){
        return method;
    }

    @Override
    protected Block getBlockType() {
        return null;
    }
}
