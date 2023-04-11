package oop.ex6.Logic;

import oop.ex6.Logic.Accessories.CaptureBlocks;
import oop.ex6.Logic.Accessories.VariableSplitter;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.Type1Exception;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The logic runner class. This class recieves the full file line that consists of all the lines
 * in the file, and delegates the analysis and breakdown of this line to different classes
 * in the logic package of this program.
 */
public class LogicRunner {

    /**
     * The array list of blocks, which is an arrayList of arrays of strings
     */
    ArrayList<String[]> blocks;
    /**
     * This array list is the list of methods within the files
     */
    ArrayList<Method> methodList = new ArrayList<>();
    /**
     * This array list is the list of global variable of the file
     */
    ArrayList<Variable> globalVariables = new ArrayList<>();
    /**
     * This array list is the list of final variable of the file
     */
    ArrayList<Variable> finalVariables = new ArrayList<>();


    // MAKE SURE TO DELETE THIS FROM THE PROGRAM  ###################################################################
//    public static void main(String[] args) throws Type1Exception, FileNotFoundException {
//        String cont = "";
//        File file = new File(args[0]);
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()) {
//            cont = cont.concat(scanner.nextLine() + "\n");
//        }
//        try {
//            MainRunner(cont);
//        } catch(Type1Exception e){
//            System.err.println("Something went wrong");
//        }
//    }

    /**
     * The main logic runner method that does the delegation
     * @param fullFile the full file line
     * @throws Type1Exception thrown when there is a logic error
     */
    public static void MainRunner(String fullFile) throws Type1Exception {
        ArrayList<String[]> blocks;
        ArrayList<Method> methodList = new ArrayList<>();
        ArrayList<Variable> globalVariables = new ArrayList<>();
        CaptureBlocks capture = new CaptureBlocks();
        ArrayList<Variable> finalVariables = new ArrayList<>();
        blocks = capture.Capture(fullFile);
        VariableSplitter splitter = new VariableSplitter(globalVariables,finalVariables,capture.getMethods());
        String[] global = capture.CaptureGlobal(fullFile);
        splitter.splitMultiples(global);
        splitter.setGlobalVars(splitter.getVarList());
        for(String[] block:blocks) {
            splitter.splitMultiples(block);
        }
    }
}
