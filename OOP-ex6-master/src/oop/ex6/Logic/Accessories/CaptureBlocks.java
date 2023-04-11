package oop.ex6.Logic.Accessories;

import oop.ex6.Logic.Accessories.Splitter.DefinitionSplitter;
import oop.ex6.Logic.Blocks.Block;
import oop.ex6.Logic.Blocks.Method;
import oop.ex6.Logic.Checkers.Checker;
import oop.ex6.Logic.Checkers.DefinitionChecker;
import oop.ex6.Logic.Checkers.MethodSignatureChecker;
import oop.ex6.Logic.Variables.Variable;
import oop.ex6.main.RegexEnum;
import oop.ex6.main.Type1Exception;
import oop.ex6.syntax.LineMatcherFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptureBlocks {
    LinkedList BlockList = new LinkedList<Block>();
    ArrayList<String> globalBlock = new ArrayList<>();
    ArrayList<Method> methodList = new ArrayList<>();

    /**
     * @param : The text containing the Block
     * @return : A list of Strings
     */

    public ArrayList<String[]> Capture(String text) throws Type1Exception {
        /* These are for counting the brackets*/
        int OpenBracketsCounter = 0;
        int ClosedBracketsCounter = 0;
        int FirstLine = 0;
        int methodLine = 1;
        boolean returnFlag = false;
        /* The final list to be returned*/
        ArrayList<String[]> finalList = new ArrayList<>();
        ArrayList<String> TempList = new ArrayList<>();
        String[] split = text.split("\n");
        for (int i = 0; i < split.length; i++) {
            String currentLine = split[i];
            /* Assuming valid input, if we find an opening bracket we return true*/
            if (currentLine.contains("{")) {
                OpenBracketsCounter++;
            }
            if(getRegexEnum(currentLine) == RegexEnum.RETURN_LINE){
                returnFlag = true;
            }
            if (getRegexEnum(currentLine) == RegexEnum.METHOD_SIGNATURE)
                {
                    Method method = CaptureMethod(currentLine);
                    if(!methodList.contains(method)) {
                        methodList.add(method);
                    }
                }

                if (currentLine.contains("}")) {
                    OpenBracketsCounter--;
                }
                TempList.add(split[i]);

                currentLine = split[i];
                if((OpenBracketsCounter > methodLine) && ((getRegexEnum(currentLine) == RegexEnum.METHOD_SIGNATURE))){
                    throw new Type1Exception();
            }
                if (OpenBracketsCounter == 0) {
                    if(getRegexEnum(currentLine) == RegexEnum.METHOD_CALL){
                        throw new Type1Exception();
                    }
                    if(getRegexEnum(currentLine) == RegexEnum.IF_AND_WHILE){
                        throw new Type1Exception();
                    }
                    if (TempList.size() == 1) {
                        globalBlock.add(currentLine);
                        TempList = new ArrayList<>();
                    }
                    if (TempList.size() > 1) {
                        finalList.add(CreateArray(TempList));
                        TempList = new ArrayList<>();
                    }
                }
            }
            return finalList;
        }

        private RegexEnum getRegexEnum (String line){
            for (RegexEnum type : RegexEnum.values()) {
                Matcher currMatcher = LineMatcherFactory.selectMatcher(type, line);
                if (currMatcher.matches()) {
                    return type;
                }
            }
            return null;
    }

    private Method CaptureMethod(String line){
        int depth = 1;
        MethodSignatureChecker checker = new MethodSignatureChecker(null,null, line,methodList,
                depth);
        String name = checker.getMethodName();
        Method method = new Method(name);
        if(checker.getVariables() != null){
            method.addVariables(checker.getVariables());
            for(Variable var: checker.getVariables()){
//                System.out.println(var.getName()+var.getType());
            }
        }
//        System.out.println(method.getName());
        return method;
    }

    public ArrayList<Method> getMethods(){
        return methodList;
    }

    public String[] CaptureGlobal(String text) throws Type1Exception {
        String var_regex = ".*(int|boolean|char|double|String).*?;";
        Pattern varpattern = Pattern.compile(var_regex);
        ArrayList<String[]> MethodBlocks = Capture(text);
        ArrayList<String> MethodLines = new ArrayList<>();
        ArrayList<String> globalLines = new ArrayList<>();

        for (String[] block : MethodBlocks){
            MethodLines.addAll(Arrays.asList(block));
        }

        String[] split = text.split("\n");
        for(String line:split){
            if (!MethodLines.contains(line)){
                globalLines.add(line);
            }
        }

        String[] globallines = new String[globalLines.size()];
        for(int i=0; i<globalLines.size();i++){
            Matcher matcher = varpattern.matcher(globalLines.get(i));
            if(matcher.matches()){
                DefinitionSplitter splitter = new DefinitionSplitter(globalLines.get(i));
            }
            globallines[i] = globalLines.get(i);
        }

    return globallines;
    }

    public String[] CreateArray(ArrayList<String> lines){
        String[] block = new String[lines.size()];
        for(int i=0; i<block.length;i++){
            block[i] = lines.get(i);
        }
        return block;
    }
}
