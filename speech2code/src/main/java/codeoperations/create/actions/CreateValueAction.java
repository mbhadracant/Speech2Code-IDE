package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mb on 22/01/2017.
 */
public class CreateValueAction extends CodeAction {

    String codeToInsert;
    Map<String, String> expressionValueMap = new HashMap();

    public CreateValueAction() {
        expressionValueMap.put("zero", "0");
        expressionValueMap.put("one", "1");
        expressionValueMap.put("two", "2");
        expressionValueMap.put("three", "3");
        expressionValueMap.put("four", "4");
        expressionValueMap.put("five", "5");
        expressionValueMap.put("six", "6");
        expressionValueMap.put("seven", "7");
        expressionValueMap.put("eight", "8");
        expressionValueMap.put("nine", "9");
        expressionValueMap.put("space", " ");
        expressionValueMap.put("true", "true");
        expressionValueMap.put("false", "false");
        expressionValueMap.put("and", "&& ");
        expressionValueMap.put("not", "! ");
        expressionValueMap.put("equals equals", "==");
        expressionValueMap.put("equals", "= ");
        expressionValueMap.put("not equals", "!= ");
        expressionValueMap.put("more than", ">");
        expressionValueMap.put("less than", "<");
        expressionValueMap.put("more or equals", ">=");
        expressionValueMap.put("less or equals", "<=");
        expressionValueMap.put("plus", "+");
        expressionValueMap.put("minus", "-");
        expressionValueMap.put("times", "*");
        expressionValueMap.put("divide", "/");
        expressionValueMap.put("modulus", "%");
        expressionValueMap.put("minus minus", "--");
        expressionValueMap.put("plus plus", "++");
        expressionValueMap.put("string", "String ");
        expressionValueMap.put("int", "int ");
        expressionValueMap.put("semi colon", ";");
        expressionValueMap.put("alpha", "a");
        expressionValueMap.put("bravo", "b");
        expressionValueMap.put("charlie", "c");
        expressionValueMap.put("delta", "d");
        expressionValueMap.put("echo", "e");
        expressionValueMap.put("fox", "f");
        expressionValueMap.put("golf", "g");
        expressionValueMap.put("hotel", "h");
        expressionValueMap.put("india", "i");
        expressionValueMap.put("juliet", "j");
        expressionValueMap.put("kilo", "k");
        expressionValueMap.put("lima", "l");
        expressionValueMap.put("mike", "m");
        expressionValueMap.put("november", "n");
        expressionValueMap.put("oscar", "o");
        expressionValueMap.put("papa", "p");
        expressionValueMap.put("quebec", "q");
        expressionValueMap.put("romeo", "r");
        expressionValueMap.put("sierra", "s");
        expressionValueMap.put("tango", "t");
        expressionValueMap.put("uniform", "u");
        expressionValueMap.put("victor", "v");
        expressionValueMap.put("whiskey", "w");
        expressionValueMap.put("x-ray", "x");
        expressionValueMap.put("yankee", "y");
        expressionValueMap.put("zulu", "z");
        expressionValueMap.put("capital alpha", "A");
        expressionValueMap.put("capital bravo", "B");
        expressionValueMap.put("capital charlie", "C");
        expressionValueMap.put("capital delta", "D");
        expressionValueMap.put("capital echo", "E");
        expressionValueMap.put("capital fox", "F");
        expressionValueMap.put("capital golf", "G");
        expressionValueMap.put("capital hotel", "H");
        expressionValueMap.put("capital india", "I");
        expressionValueMap.put("capital juliet", "J");
        expressionValueMap.put("capital kilo", "K");
        expressionValueMap.put("capital lima", "L");
        expressionValueMap.put("capital mike", "M");
        expressionValueMap.put("capital november", "N");
        expressionValueMap.put("capital oscar", "O");
        expressionValueMap.put("capital papa", "P");
        expressionValueMap.put("capital quebec", "Q");
        expressionValueMap.put("capital romeo", "R");
        expressionValueMap.put("capital sierra", "S");
        expressionValueMap.put("capital tango", "T");
        expressionValueMap.put("capital uniform", "U");
        expressionValueMap.put("capital victor", "V");
        expressionValueMap.put("capital whiskey", "W");
        expressionValueMap.put("capital x-ray", "X");
        expressionValueMap.put("capital yankee", "Y");
        expressionValueMap.put("capital zulu", "Z");



    }

    @Override
    public void init() {

    }

    @Override
    public void set(String parameter) {
        if(parameter.length() == 1) {
            if(Character.isLetter(parameter.charAt(0))) {
                codeToInsert = parameter;
            }
        } else {
            if(expressionValueMap.containsKey(parameter)) {
                codeToInsert = expressionValueMap.get(parameter);
            } else {
                codeToInsert = "";
            }
        }
    }

    @Override
    public void execute(JavaCodeArea editor) {
        try {
            Platform.runLater(() -> editor.insertText(editor.getCaretPosition(), codeToInsert));
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
