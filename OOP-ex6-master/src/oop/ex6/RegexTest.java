package oop.ex6;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {

        String string = "    a =                  c;";
        Pattern pattern = Pattern.compile("[a-zA-z_] *? = *? [a-zA-z_] *? *?;");
        Matcher matcher = pattern.matcher(string);
        ArrayList<String> vars = new ArrayList<>();
        while(matcher.find()){
            int StartIndex = matcher.start();
            int LastIndex = matcher.end();
            String substring = string.substring(StartIndex,LastIndex);
            String[] split = substring.split(" ");
            ArrayList<String> irrelevant = new ArrayList<>();
            irrelevant.add("&&");
            irrelevant.add("||");
            irrelevant.add("(");
            irrelevant.add(")");
            irrelevant.add("");
            irrelevant.add(" ");
            irrelevant.add("=");
            irrelevant.add(";");
            for(String letter : split) {
                if (!letter.equals(" ")) {
                    if (!irrelevant.contains(letter)) {
                        if (letter.contains(";")){
                            letter = letter.replace(";","");
                        }
                        vars.add(letter);
                    }
                }
            }
        }
    }
}