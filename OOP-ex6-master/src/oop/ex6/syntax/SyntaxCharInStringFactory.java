package oop.ex6.syntax;
import java.util.regex.*;

/**
 * The SyntaxCharInStringFactory class, is a Pattern compilation and matcher creating factory. Used for
 * compiling patterns and matchers for basic syntax found within strings, to distinguish them from times
 * when they are used outside of strings.
 */
public class SyntaxCharInStringFactory {

    /**
     * The regex for semicolons in strings
     */
    private static final String SEMICOLON_IN_STRING = "([\"']).*?;.*?\\1";
    /**
     * The regex for open brackets in strings
     */
    private static final String OPEN_BRACKET_IN_STRING = "([\"']).*?\\{.*?\\1";
    /**
     * The regex for closed brackets in strings
     */
    private static final String CLOSE_BRACKET_IN_STRING = "([\"']).*?}.*?\\1";
    /**
     * The regex for comment slashes in strings
     */
    private static final String DOUBLE_SLASH_IN_STRING = "([\"]).*?//.*?\\1";
    /**
     * The regex for open parentheses in strings
     */
    private static final String OPEN_PARENTH_IN_STRING = "([\"']).*?\\(.*?\\1";
    /**
     * The regex for closed parentheses in strings
     */
    private static final String CLOSE_PARENTH_IN_STRING = "([\"']).*?\\).*?\\1";
    /**
     * Represents the semicolon syntax
     */
    private static final String SEMICOLON = ";";
    /**
     * Represents the open bracket syntax
     */
    private static final String OPEN_BRACKET = "{";
    /**
     * Represents the closed bracket syntax
     */
    private static final String CLOSED_BRACKET = "}";
    /**
     * Represents the comment syntax command
     */
    private static final String COMMENT = "comment";
    /**
     * Represents the open parentheses syntax
     */
    private static final String OPEN_PARENTH = "(";
    /**
     * Represents the closed parentheses syntax
     */
    private static final String CLOSED_PARENTH = ")";
    /**
     * The regex for blank lines
     */
    private static final String BLANK_LINE_REGEX = "^[\\s]*$";

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
                return Pattern.compile(SEMICOLON_IN_STRING).matcher(line);
            case OPEN_BRACKET:
                return Pattern.compile(OPEN_BRACKET_IN_STRING).matcher(line);
            case CLOSED_BRACKET:
                return Pattern.compile(CLOSE_BRACKET_IN_STRING).matcher(line);
            case COMMENT:
                return Pattern.compile(DOUBLE_SLASH_IN_STRING).matcher(line);
            case OPEN_PARENTH:
                return Pattern.compile(OPEN_PARENTH_IN_STRING).matcher(line);
            case CLOSED_PARENTH:
                return Pattern.compile(CLOSE_PARENTH_IN_STRING).matcher(line);
            default:
                return Pattern.compile(BLANK_LINE_REGEX).matcher(line);
        }
    }
}
