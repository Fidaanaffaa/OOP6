package oop.ex6.main;

/**
 * The VarEnum class. Used for storing the sub-pieces of the larger line regexes within the RegexEnum class.
 * The Toolbox class has methods that iterate over parts of its stored enums in order to create
 * important regexes for other general uses.
 */
public enum VarEnum {

    VAR_NAME("(?:_[A-Za-z_]+?|[A-Za-z][A-Za-z_]*?)"),

    // variable types
    INT("int"),
    DOUBLE("double"),
    STRING("String"),
    CHAR("char"),
    BOOLEAN("boolean"),

    // variable values
    INT_VAL("(?:0|-?(?!0)\\d+?)" ),
    DOUBLE_VAL("(?:0|-?0\\.\\d*?|-?(?!0)\\d*?\\.?\\d+?)"),
    STR_VAL("\"[^\"'\\\\,]*?\""),
    CHAR_VAL("'[^\"'\\\\,]?'"),
    BOOL_VAL("(?:true|false|0|-?0\\.\\d*?|-?(?!0)\\d*?\\.?\\d+?)"),

    // all the variable possibility values, including variable assignments
    INT_VAL_FULL(variableAssignment(INT_VAL.getRegex())),
    DOUBLE_VAL_FULL(variableAssignment(DOUBLE_VAL.getRegex())),
    STR_VAL_FULL(variableAssignment(STR_VAL.getRegex())),
    CHAR_VAL_FULL(variableAssignment(CHAR_VAL.getRegex())),
    BOOL_VAL_FULL(variableAssignment(BOOL_VAL.getRegex())),

    // variable values with equals signs
    INT_EQUALS(variableEquals(INT_VAL_FULL.getRegex())),
    DOUBLE_EQUALS(variableEquals(DOUBLE_VAL_FULL.getRegex())),
    STR_EQUALS(variableEquals(STR_VAL_FULL.getRegex())),
    CHAR_EQUALS(variableEquals(CHAR_VAL_FULL.getRegex())),
    BOOL_EQUALS(variableEquals(BOOL_VAL_FULL.getRegex())),

    // other important enums

//    VAR_NAME_FOR_APPENDING("_[A-Za-z_]+?|[A-Za-z][A-Za-z_]*?)"),
    METHOD_NAME("[A-Za-z][A-Za-z_]*?"),
    VAR_OPTIONS("(?:int|double|char|String|boolean)"),
    METHOD_PARAM_NAME("[ \t]*?" + VAR_OPTIONS.getRegex() + "[ \t]?" + VAR_NAME.getRegex()),
    FINAL_MODIFIER("(?:final[ \t])?[ \t]*?"),
    IF_WHILE_CONDITIONS("(" + BOOL_VAL.getRegex() + "|" +
            VarEnum.VAR_NAME.getRegex() + ")"),
    ;

    /**
     * the regex piece of a specific larger line regex
     */
    private final String regex;

    /**
     * The constructor for the VarEnum class.
     * @param regularExpression the regex constant
     */
    VarEnum(String regularExpression) {
        regex = regularExpression;
    }

    /**
     * Builds the regex sub-expression for a type equaling a certain value
     * @param type the type of the value
     * @return the regex sub-expression with equals
     */
    private static String variableEquals(String type){
        return "(?:[ \t]*?=[ \t]*?" + "(?:" + type + "|"+ VAR_NAME.getRegex() + "))?";
    }

    /**
     * This method accounts for variable assignments in regexes
     * @param type the type to add the assignment too
     * @return the updated regex
     */

    private static String variableAssignment(String type){
        return type + "|" + VAR_NAME.getRegex();
    }

    /**
     * the getter for the regular expression representing the line
     */
    public String getRegex() {
        return regex;
    }

}

