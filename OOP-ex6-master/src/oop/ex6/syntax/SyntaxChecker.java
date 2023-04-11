package oop.ex6.syntax;
import oop.ex6.main.RegexEnum;
import oop.ex6.main.Type1Exception;

import java.util.regex.*;



/**
 * The class for checking basic syntax such as // for comments, ;,{, and } for all lines and
 * parentheses being in the correct format. Also checks full and correct line syntax
 */
public class SyntaxChecker {

    /**
     * The new line to be checked
     */
    private String newLine;
    /**
     * the current matcher being used to check the syntax
     */
    private Matcher currMatcher;
    /**
     * used for storing whether found a correct line ending
     */
    private boolean found = false;
    /**
     * An array of constants of end syntax types
     */
    private String[] endSyntax = new String[]{";", "{","}"};
    /**
     * A string representing the comment syntax type
     */
    private static final String COMMENT = "comment";
    /**
     * The double-slash for comments in files
     */
    private static final String DOUBLE_SLASH = "//";
    /**
     * A string representing the blank line syntax type
     */
    private static final String BLANK_LINE = "blank_line";
    /**
     * Represents being multiplied by 2
     */
    private static final int HALVED = 2;
    /**
     * represents a single amount of times a character shows up
     */
    private static final int SINGLE_AMOUNT = 1;
    /**
     * represents an empty string
     */
    private static final String EMPTY_STRING = "";
    /**
     * Represents a lack of amount
     */
    private static final int NO_VALUE = 0;


    /**
     * Matcher getter from the EndSyntaxFactory
     * @param syntaxType the pattern to get
     * @param line the line for the matcher
     */
    private static Matcher getBasicSyntaxMatcher(String syntaxType, String line) {
        return BasicSyntaxFactory.selectMatcher(syntaxType, line);
    }

    /**
     * Matcher getter from the SyntaxCharInStringFactory
     * @param syntaxType the pattern to get
     * @param line  the line for the matcher
     */
    private static Matcher getSyntaxInStringMatcher(String syntaxType, String line) {
        return SyntaxCharInStringFactory.selectMatcher(syntaxType, line);
    }

    /**
     * The getter for the main LineMatcherFactory
     * @param syntaxType the pattern to get
     * @param line the line for the matcher
     */
    private static Matcher getMatcher(RegexEnum syntaxType, String line){
        return LineMatcherFactory.selectMatcher(syntaxType, line);
    }

    /**
     * The central syntax checking method that calls all the other syntax checking sub methods
     * @throws Type1Exception   Throws a type1 exception for some sort of illegal syntax exception
     */
    public void checkSyntax(String line) throws Type1Exception {
        newLine = line;
        if (checkBlankLine()){
            return;
        }
        if (checkComment()){
            return;
        }
        checkEndOfLineSyntax();
        checkMatchingSyntax();
    }

    /**
     * Checks if this is a blank line or not. If not, the program continues
     */
    private boolean checkBlankLine() {
        currMatcher = getBasicSyntaxMatcher(BLANK_LINE, newLine);
            return currMatcher.matches();
    }

    /**
     * Checks that the comments meets the comment syntax requirements. Accounts for comment
     * syntax that is in strings and chars
     * @throws IllegalSyntaxException If have doubleSlash anywhere else in line
     */
    private boolean checkComment() throws IllegalSyntaxException {
        if (newLine.contains(DOUBLE_SLASH)) {
            currMatcher = getBasicSyntaxMatcher(COMMENT, newLine);
            if (currMatcher.find()) {
                return true;
            }
            int stringAmount = middleOfStringChecker(COMMENT);
            int fullCount = (newLine.length() -
                    newLine.replace(DOUBLE_SLASH, EMPTY_STRING).length()) / HALVED;
            if (stringAmount < fullCount) {
                System.err.println("Error: Illegal comment syntax");
                throw new IllegalSyntaxException();
            }
        }
        return false;
    }

    /**
     * Checks that the line meets the line ending syntax requirements. Accounts for line
     * syntax that is in strings and chars
     * @throws IllegalSyntaxException Thrown if there is an illegal line ending syntax in a place
     *  other than in the end of the line and not in a string or char, or thrown if lacks line
     *  ending syntax altogether
     */
    private void checkEndOfLineSyntax() throws IllegalSyntaxException {
        for (String type : endSyntax) {
            currMatcher = getBasicSyntaxMatcher(type, newLine);
            if (currMatcher.find()) {
                found = true;
                int legalAmount = middleOfStringChecker(type);
                // counts amount of times the character is in the line
                int fullCount = newLine.length() - newLine.replace(type, EMPTY_STRING).length();
                if (legalAmount + SINGLE_AMOUNT < fullCount) {
                    System.err.println("Error: Illegal line ending syntax in middle of line");
                    throw new IllegalSyntaxException();
                }
            }
        }
        if (!found) {
            System.err.println("Error: Lack of line ending syntax");
            throw new IllegalSyntaxException();
        }
        found = false;  // reset boolean for use by other methods
    }

    /**
     * The matching checker, tries to see if the line that passed all the prior checks matches
     * with any line that is allowed
     * @throws IllegalSyntaxException if the line doesn't match any pattern, thrown
     */
    private void checkMatchingSyntax() throws IllegalSyntaxException {
        for (RegexEnum type : RegexEnum.values()) {
            currMatcher = getMatcher(type, newLine);
            if (currMatcher.matches()) {
                return;
            }
        }
        System.err.println("Error: This line has invalid syntax");
        throw new IllegalSyntaxException();
    }


    /**
     * This method checks for general syntax characters in the middle of strings and chars
     * @param syntax the syntax character or sequence
     * @return an int of how many times this case was found
     */
    private int middleOfStringChecker(String syntax)  {
        Matcher syntaxMatcher = getSyntaxInStringMatcher(syntax, newLine);
        int counter = NO_VALUE;
        while (syntaxMatcher.find()){
            counter++;
        }
        return counter;
    }

}
