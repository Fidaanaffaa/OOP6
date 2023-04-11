package oop.ex6.Logic.Accessories;

import oop.ex6.Logic.Variables.*;
import oop.ex6.Logic.Variables.BooleanVar;
import oop.ex6.Logic.Variables.DoubleVar;
import oop.ex6.Logic.Variables.IntegerVar;

public class VariableFactory {
    private final String Integer = "int";
    private final String String = "String";
    final String Boolean = "boolean";
    final String Double = "double";
    final String Char = "char";

    public VariableFactory() {
    }

    public Variable createVar(String type, String name, int depth, boolean initialized, boolean isfinal,
                              boolean isglobal) {
        switch (type) {
            case Integer:
                return new IntegerVar(name, depth, initialized, isfinal, isglobal,type);
            case String:
                return new StringVar(name, depth, initialized, isfinal, isglobal,type);
            case Boolean:
                return new BooleanVar(name, depth, initialized, isfinal, isglobal,type);
            case Double:
                return new DoubleVar(name, depth, initialized, isfinal, isglobal,type);
            case Char:
                return new CharVar(name, depth, initialized, isfinal, isglobal,type);
        }
        return null;
    }
}