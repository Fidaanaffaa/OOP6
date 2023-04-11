package oop.ex6.main;

import oop.ex6.syntax.ToolBox;

/**
 * The RegexEnum class. This class stores all the constants for lines that are directly put into
 * matchers to match full and complete lines for proper syntax. Contains getters but also
 * regex building and final modifying methods for certain regex adjustments or creations that
 * are more general and apply to several constants
 */
public enum RegexEnum {

    OPEN_BRACKET_LINE("^\\s*?\\{[ \t]*?$", "open_bracket"),

    CLOSED_BRACKET_LINE("^\\s*?\\}[ \t]*?$","closed_bracket"),

    INT_VAR_LINE(variableRegexBuilder(VarEnum.INT.getRegex(), VarEnum.INT_EQUALS.getRegex()),
            "int_variable"),

    DOUBLE_VAR_LINE(variableRegexBuilder(VarEnum.DOUBLE.getRegex(), VarEnum.DOUBLE_EQUALS.getRegex()),
            "double_variable"),

    STRING_VAR_LINE(variableRegexBuilder(VarEnum.STRING.getRegex(), VarEnum.STR_EQUALS.getRegex()),
            "string_variable"),

    CHAR_VAR_LINE(variableRegexBuilder(VarEnum.CHAR.getRegex(), VarEnum.CHAR_EQUALS.getRegex()),
            "char_variable"),

    BOOL_VAR_LINE(variableRegexBuilder(VarEnum.BOOLEAN.getRegex(), VarEnum.BOOL_EQUALS.getRegex()),
            "boolean_variable"),

    REFERENCE_VAR_LINE("\\s*?" + VarEnum.VAR_NAME.getRegex() + "(?:[ \t]*?=[ \t]*?" +
            "(?:" + ToolBox.instance().getParamValueRegex() + "))[ \t]*?;[ \t]*?",
            "variable_reference"),

    RETURN_LINE("\\s*?return[ \t]*?;[ \t]*?", "return"),

    METHOD_SIGNATURE("\\s*?void[ \t]+?" + VarEnum.METHOD_NAME.getRegex() +
            "[ \t]*?\\([ \t]*?" + "(?:" + VarEnum.FINAL_MODIFIER.getRegex() +
            VarEnum.METHOD_PARAM_NAME.getRegex() + "*?" + "(?<=[A-Za-z_])" + "(?:[ \t]*?,[ \t]*?" +
            VarEnum.FINAL_MODIFIER.getRegex() + VarEnum.METHOD_PARAM_NAME.getRegex() +
            ")*?)?" + "[ \t]*?\\)[ \t]*?\\{[ \t]*?$",
            "method_signature"),

    METHOD_CALL("\\s*?"+ VarEnum.METHOD_NAME.getRegex() + "[ \t]*?\\([ \t]*?" + "(?:(?:" +
            ToolBox.instance().getParamValueRegex() + ")?" + "(?<=\"|'|\\w)" + "(?:[ \t]*?,[ \t]*?" + "(?:"+
            ToolBox.instance().getParamValueRegex() + "))*?)?" + "[ \t]*?\\)[ \t]*?;[ \t]*?$"
            , "method_call"),

    IF_AND_WHILE("\\s*?(if|while)[ \t]*?\\([ \t]*?" + VarEnum.IF_WHILE_CONDITIONS.getRegex()+
            "(?<=\\w)"+ "(?:[ \t]*?(\\|\\||&&)[ \t]*?" + VarEnum.IF_WHILE_CONDITIONS.getRegex() +
            ")*?"+ "[ \t]*?\\)[ \t]*?\\{[ \t]*?$", "if_or_while"),
    ;

    /**
     * the regex constant of each line type
     */
    private String regex;
    /**
     * the syntax type associated with the regex
     */
    private String syntaxType;

    /**
     * the enum constructor called by each enum for set up
     * @param regularExpression the regex constant
     * @param syntax the syntax type associated
     */
    RegexEnum(String regularExpression, String syntax) {
        regex = regularExpression;
        syntaxType = syntax;
    }

    /**
     * the regex building method for variables
     * @param type the type of the variable the regex is for
     * @param equals the values that the variable can be equal to
     * @return the newly built regex
     */
    private static String variableRegexBuilder(String type, String equals){
        return finalModifier(type) + "\\s*?" + type + "[ \t]+?" + VarEnum.VAR_NAME.getRegex() + equals +
                "(?:[ \t]*?,[ \t]*?" + "(?:(?:final[ \t]+?)?(?=" + VarEnum.VAR_NAME.getRegex() +
                "[ \t]*?=[ \t]*?))*?"+VarEnum.VAR_NAME.getRegex() +equals + ")*?[ \t]*?;[ \t]*?";
    }

    /**
     * Adds into a variable regex a possible final modifier in the beginning of a string
     * @param type the type declaration of the variable being made final
     * @return string with custom added final modifier
     */
    private static String finalModifier(String type){
        return "\\s*?(?:(?:final[\t ]+?)?(?=[ \t]*?" + type + "[ \t]+?" + VarEnum.VAR_NAME.getRegex() +
                "[ \t]*?=[ \t]*?))*?";
    }

    /**
     * the getter for the regular expression representing the line
     */
    public String getRegex(){
        return regex;
    }

    /**
     * prints out the syntax type
     */
    @Override
    public String toString() {
        return syntaxType;
    }
}
