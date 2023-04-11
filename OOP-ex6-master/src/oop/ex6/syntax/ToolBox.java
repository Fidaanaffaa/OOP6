package oop.ex6.syntax;

import oop.ex6.main.VarEnum;

/**
 * This class contains tools that are used across the program for different purposes. It is a
 * singleton, and its tools are in the form of strings that are created once and then they are retrieved
 * via getters
 */
public class ToolBox {

    /**
     * Represents a pipeline, used for the "or" statement in regexes
     */
    private static final String PIPE = "|";
    /**
     * The string representing the variable options within the program
     */
    private String varOptions = "";
    /**
     * The string representing the value options for each variable within the program
     */
    private String valueOptions = "";
    /**
     * Represents the fifth enum index
     */
    private static final int SIXTH_INDEX = 5;
    /**
     * Represents the seventh enum index
     */
    private static final int EIGHTH_INDEX = 7;
    /**
     * Represents the tenth enum index
     */
    private static final int ELEVENTH_INDEX = 10;
    /**
     * The instance of this singleton toolbox
     */
    private static final ToolBox instance = new ToolBox();

    /**
     * The private toolbox constructor. Calls its tool methods to create them for the only time
     */
    private ToolBox(){
        variableTypeRegex();
        paramValueRegex();
    }

    /**
     * This method creates the string pattern for the regex of all the possible program variables
     */
    private String variableTypeRegex() {
        for(VarEnum item: VarEnum.values()) {
            if (item.ordinal() == SIXTH_INDEX){
                varOptions += item.getRegex();
                break;
            } else if (item.ordinal() < SIXTH_INDEX && item.ordinal() > 0) {
                varOptions += item.getRegex() + PIPE;
            }
        }
        return varOptions;
    }

    /**
     * This method creates the string pattern regex for all the possible parameter values for
     * method calls
     */
    private String paramValueRegex(){
        for(VarEnum item: VarEnum.values()) {
            if (item.ordinal() == ELEVENTH_INDEX){
                valueOptions += item.getRegex() + PIPE + VarEnum.VAR_NAME.getRegex();
                break;
            } else if (item.ordinal() > EIGHTH_INDEX && item.ordinal() < ELEVENTH_INDEX) {
                valueOptions += item.getRegex() + PIPE;
            }
        }
        return valueOptions;
    }

    /**
     * getter for the regex of all possible parameter values
     */
    public String getParamValueRegex(){
        return valueOptions;
    }

    /**
     * getter for the regex of all possible sJava file variables
     */
    public String getVariableTypeRegex(){
        return varOptions;
    }

    /**
     * The getter for this toolbox instance
     */
    public static ToolBox instance(){
        return instance;
    }
}
