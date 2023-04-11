package oop.ex6.Logic.Checkers;

import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.syntax.LineMatcherFactory;
import oop.ex6.main.RegexEnum;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckFactory {
    private ArrayList<Variable> variablesList;
    private ArrayList<Variable> globalVariables;
    private String variableToCheck;
    private int depth;
    private ArrayList<Method> methodList;
    final String If = "if";
    final String While = "while";
    final String MethodSignature = "void";

    //    final String ifWhile = RegexEnum.IF_AND_WHILE.toString();
//    final String methodCall = RegexEnum.METHOD_CALL.toString();
//    final String referenceLine = RegexEnum.REFERENCE_VAR_LINE.toString();
//    final String methodSignature = RegexEnum.METHOD_SIGNATURE.toString();
    public CheckFactory(ArrayList<Variable> VarsList, ArrayList<Variable> globalVarsList, ArrayList<Method> methodList,
                        String lineToCheck, int depth) {
        this.variablesList = VarsList;
        this.globalVariables = globalVarsList;
        this.variableToCheck = lineToCheck;
        this.depth = depth;
        this.methodList = methodList;
    }

    public Checker createChecker(String lineToCheck) {
        String var_regex = ".*(int|boolean|char|double|String).*?;";
        Pattern varpattern = Pattern.compile(var_regex);
        Matcher matcher = varpattern.matcher(lineToCheck);
        if (matcher.matches()) {
            return new DefinitionChecker(variablesList, globalVariables, lineToCheck, methodList, depth);
        }
        if (getRegexEnum(lineToCheck) == null){
            return null;
        }
        switch (Objects.requireNonNull(getRegexEnum(lineToCheck))) {
            case IF_AND_WHILE:
                return new IfWhileChecker(variablesList, globalVariables, lineToCheck, methodList,depth);
            case METHOD_CALL:
                return new MethodChecker(variablesList, globalVariables, lineToCheck, depth, methodList);
            case REFERENCE_VAR_LINE:
                return new AssignmentChecker(variablesList, globalVariables, lineToCheck, methodList, depth);
            case METHOD_SIGNATURE:
                return new MethodSignatureChecker(variablesList, globalVariables, lineToCheck, methodList, depth);
        }
        return null;
    }

    private RegexEnum getRegexEnum(String line) {
        for (RegexEnum type : RegexEnum.values()) {
            Matcher currMatcher = LineMatcherFactory.selectMatcher(type, line);
            if (currMatcher.matches()) {
                return type;
            }
        }
        return null;
    }
//    public Matcher getMatcher(String line){
//
//        }
    }
