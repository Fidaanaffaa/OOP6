package oop.ex6.syntax;
import oop.ex6.main.RegexEnum;

import java.util.regex.*;

/**
 * The LineMatcherFactory class, is a Pattern compilation and matcher creating factory. Used for
 * compiling patterns and matchers for full and proper line syntax, making matchers for all the
 * various types of lines such as variable declaration lines, method signature lines, etc.
 */
public class LineMatcherFactory {

    /**
     * The open bracket line pattern
     */
    private static Pattern pOpenBracket;
    /**
     * The closed bracket line pattern
     */
    private static Pattern pClosedBracket;
    /**
     * The int declaration line pattern
     */
    private static Pattern pIntLine;
    /**
     * The double declaration line pattern
     */
    private static Pattern pDoubleLine;
    /**
     * The String declaration line pattern
     */
    private static Pattern pStringLine;
    /**
     * The char declaration line pattern
     */
    private static Pattern pCharLine;
    /**
     * The boolean declaration line pattern
     */
    private static Pattern pBoolLine;
    /**
     * The alternate assignment reference variable line pattern
     */
    private static Pattern pReferenceVariable;
    /**
     * The return line pattern
     */
    private static Pattern pReturnLine;
    /**
     * The method signature line pattern
     */
    private static Pattern pMethodSignatureLine;
    /**
     * The method call line pattern
     */
    private static Pattern pMethodCallLine;
    /**
     * The if or while line pattern
     */
    private static Pattern pIfWhileLine;
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
    public static Matcher selectMatcher(RegexEnum syntaxType, String line) {

        switch (syntaxType) {
            case OPEN_BRACKET_LINE:
                pOpenBracket = Pattern.compile(syntaxType.getRegex());
                return pOpenBracket.matcher(line);
            case CLOSED_BRACKET_LINE:
                pClosedBracket = Pattern.compile(syntaxType.getRegex());
                return pClosedBracket.matcher(line);
            case INT_VAR_LINE:
                pIntLine = Pattern.compile(syntaxType.getRegex());
                return pIntLine.matcher(line);
            case DOUBLE_VAR_LINE:
                pDoubleLine = Pattern.compile(syntaxType.getRegex());
                return pDoubleLine.matcher(line);
            case STRING_VAR_LINE:
                pStringLine = Pattern.compile(syntaxType.getRegex());
                return pStringLine.matcher(line);
            case CHAR_VAR_LINE:
                pCharLine = Pattern.compile(syntaxType.getRegex());
                return pCharLine.matcher(line);
            case BOOL_VAR_LINE:
                pBoolLine = Pattern.compile(syntaxType.getRegex());
                return pBoolLine.matcher(line);
            case REFERENCE_VAR_LINE:
                pReferenceVariable = Pattern.compile(syntaxType.getRegex());
                return pReferenceVariable.matcher(line);
            case RETURN_LINE:
                pReturnLine = Pattern.compile(syntaxType.getRegex());
                return pReturnLine.matcher(line);
            case METHOD_SIGNATURE:
                pMethodSignatureLine = Pattern.compile(syntaxType.getRegex());
                return pMethodSignatureLine.matcher(line);
            case METHOD_CALL:
                pMethodCallLine = Pattern.compile(syntaxType.getRegex());
                return pMethodCallLine.matcher(line);
            case IF_AND_WHILE:
                pIfWhileLine = Pattern.compile(syntaxType.getRegex());
                return pIfWhileLine.matcher(line);
            default:
                return Pattern.compile(BLANK_LINE_REGEX).matcher(line);
        }
    }
}
