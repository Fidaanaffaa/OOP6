package oop.ex6.main;
import oop.ex6.Logic.LogicRunner;
import oop.ex6.syntax.*;
import java.io.*;
import java.util.Scanner;


/**
 * The Sjavac class that has the main method inside it. It receives the Sjava file from the command line
 * and then runs all the main checks on the file. This class uses a scanner to extract the lines and
 * then inserts these lines into the syntaxChecking class and then passes lines with correct syntax
 * to the logic processing classes to check the logic of the code in the Sjava file.
 */
public class Sjavac {

    /**
     * The sJava file being analyzed
     */
    static File sJavaFile;
    /**
     * The scanner for extracting lines from the sJava file
     */
    static Scanner scanner;
    /**
     * The syntax checker object for each line extracted by the scanner
     */
    static SyntaxChecker syntaxChecker;
    /**
     * Represents the first index of an array
     */
    private static final int FIRST_INDEX = 0;
    /**
     * Represents the printed value if file has passed all checks
     */
    private static final int SUCCESS = 0;
    /**
     * Represents the printed value if file has failed a syntax or logic check
     */
    private static final int CHECKING_ERROR = 1;
    /**
     * Represents the printed value if file has a problematic IO error
     */
    private static final int IO_ERROR = 2;
    /**
     * Represents the expected arg amount from command line
     */
    private static final int ARG_AMOUNT = 1;
    /**
     * The full file in a string
     */
    private static String fullFileLine = "";
    /**
     * The space string character
     */
    private static final String SPACE = " ";
    /**
     * The line break string character
     */
    private static final String LINE_SPACE = "\n";




    /**
     * The constructor of the Sjavac class
     * @param file the Sjava file to be analyzed
     * @throws IOException from the scanner or from other general problems with the file
     */
    Sjavac(File file) throws IOException {
        sJavaFile = file;
        scanner = new Scanner(sJavaFile);
        syntaxChecker = new SyntaxChecker();
    }

    /**
     * Runs the whole program.
     * @throws Type1Exception if there is a syntax error or logic error
     */
    void runProgram() throws Type1Exception {
//        int count = 0;
        while (scanner.hasNextLine()) {
//            count++;
//            System.out.println("lineCount is " + count);
            String line = scanner.nextLine();
            syntaxChecker.checkSyntax(line);
            fullFileLine += line + LINE_SPACE;
        }
        LogicRunner.MainRunner(fullFileLine);
    }

    /**
     * The program's main method
     */
    public static void main(String[] args) {
        File sourceFile;
        boolean correctNumArgs = false;
        try {
            if (args.length != ARG_AMOUNT || args[FIRST_INDEX].contains(SPACE)) {
                System.err.println("Error: Too many arguments");
                throw new IOException();
            }
            correctNumArgs = true;
            sourceFile = new File(args[FIRST_INDEX]);
            try{
                oop.ex6.main.Sjavac sJavaFileAnalyzer = new oop.ex6.main.Sjavac(sourceFile);
                sJavaFileAnalyzer.runProgram();
                System.out.println(SUCCESS);
            } catch (Type1Exception t) {  // logic or syntax errors
                System.out.println(CHECKING_ERROR);
            }
        } catch (IOException i) {  // IO errors
            if (correctNumArgs) {
                System.err.println("Error: This is not file");
            }
            System.out.println(IO_ERROR);
        }
    }
}
