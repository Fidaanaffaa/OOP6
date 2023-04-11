package oop.ex6.syntax;
import java.util.regex.*;

/**
 * The BasicSyntaxFactory class, is a Pattern compilation and matcher creating factory. Used for
 * compiling patterns and matchers for basic syntax, such as semicolons or comment slashes. Also
 * used for making the pattern and matcher for the variable option possibilities.
 */
public class BasicSyntaxFactory {

    /**
     * Represents the semicolon syntax
     */
    private static final String SEMICOLON = ";";
    /**
     * The regex for semicolons at the end of lines
     */
    private static final String SEMICOLON_REGEX = " *?; *?$";
    /**
     * Represents the closing bracket syntax
     */
    private static final String CLOSE_BRACKET = "}";
    /**
     * The regex for closing brackets at the end of lines
     */
    private static final String CLOSE_BRACKET_REGEX = " *?} *?$";
    /**
     * Represents the opening bracket syntax
     */
    private static final String OPEN_BRACKET = "{";
    /**
     * The regex for opening brackets at the end of lines
     */
    private static final String OPEN_BRACKET_REGEX = " *?\\{ *?$";
    /**
     * Represents the comment syntax
     */
    private static final String COMMENT = "comment";
    /**
     * The regex for comment slashes at the beginning of lines
     */
    private static final String COMMENT_REGEX = "^//";
    /**
     * The regex for blank lines
     */
    private static final String BLANK_LINE_REGEX = "^[\\s]*$";
    /**
     * The syntax command for accessing the pattern for variable membership in the program
     */
    private static final String VAR_TYPE_COMMAND = "variables";
    /**
     * Represents an empty string
     */
    private static final String EMPTY_STRING = "";
    /**
     * The string of variable possibilities in this program
     */
    private static String varOptions = "";


    /**
     * The Matcher selector method, used for selecting the appropriate pattern and matcher for the
     * appropriate task
     * @param syntaxType the type of syntax to be matched
     * @param line the line to search for the syntax inside
     * @return a Matcher object pre-prepared with a regex pattern and a line to compare it to
     */
    public static Matcher selectMatcher(String syntaxType, String line) {

        switch (syntaxType) {
            case SEMICOLON:
                return Pattern.compile(SEMICOLON_REGEX).matcher(line);
            case CLOSE_BRACKET:
                return Pattern.compile(CLOSE_BRACKET_REGEX).matcher(line);
            case OPEN_BRACKET:
                return Pattern.compile(OPEN_BRACKET_REGEX).matcher(line);
            case COMMENT:
                return Pattern.compile(COMMENT_REGEX).matcher(line);
            case VAR_TYPE_COMMAND:
                if (varOptions.equals(EMPTY_STRING)){
                    varOptions = ToolBox.instance().getVariableTypeRegex();  // creates variable options regex
                }
                return Pattern.compile(varOptions).matcher(line);
            default:
                return Pattern.compile(BLANK_LINE_REGEX).matcher(line);
        }
    }
}
