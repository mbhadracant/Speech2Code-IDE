package speechanalyser;

import codeoperations.CodeAction;
import codeoperations.create.CreateCodeLookup;
import codeoperations.create.CreateCodeStatement;
import codeoperations.navigations.NavigationLookup;
import codeoperations.navigations.NavigationStatement;
import javafx.application.Platform;
import utilities.EditorNavigator;

import java.util.*;

/**
 * Created by mb on 11/01/2017.
 */
public class Analyser {

    public static boolean CAN_ONLY_SAY_LETTER_OR_DIGIT;
    private Map<String, CodeAction> codeActionMap = new HashMap();

    public Analyser() {
        codeActionMap.put("print", createCodeAction(CreateCodeStatement.PRINT));
        codeActionMap.put("print line", createCodeAction(CreateCodeStatement.PRINT_LINE));
        codeActionMap.put("create class", createCodeAction(CreateCodeStatement.CLASS));
        codeActionMap.put("create main method", createCodeAction(CreateCodeStatement.MAIN));
        codeActionMap.put("create string", createCodeAction(CreateCodeStatement.STRING));
        codeActionMap.put("for loop", createCodeAction(CreateCodeStatement.FOR));
        codeActionMap.put("while loop", createCodeAction(CreateCodeStatement.WHILE));
        codeActionMap.put("if statement", createCodeAction(CreateCodeStatement.IF));
        codeActionMap.put("value", createCodeAction(CreateCodeStatement.VALUE));
        codeActionMap.put("back", navigationAction(NavigationStatement.BACK));
        codeActionMap.put("next", navigationAction(NavigationStatement.NEXT));
        codeActionMap.put("previous line", navigationAction(NavigationStatement.LINE_UP));
        codeActionMap.put("next line", navigationAction(NavigationStatement.LINE_DOWN));
        codeActionMap.put("back space", navigationAction(NavigationStatement.BACKSPACE));
        codeActionMap.put("run main method", navigationAction(NavigationStatement.RUN));
        codeActionMap.put("undo", navigationAction(NavigationStatement.UNDO));
        codeActionMap.put("new line", createCodeAction(CreateCodeStatement.NEW_LINE));
    }

    public CodeAction analyse(String inputStr) {

        CodeAction action = null;

            if(codeActionMap.get(inputStr) == null) {
                action = codeActionMap.get("value");
                action.set(inputStr);
            } else {
                action = codeActionMap.get(inputStr);
            }


        if(action != null) action.init();
        return action;

    }

    private CodeAction createCodeAction (CreateCodeStatement statement) {
        CodeAction action = CreateCodeLookup.getAction(statement);
        return action;
    }

    private CodeAction navigationAction (NavigationStatement statement) {
        CodeAction action = NavigationLookup.getAction(statement);
        return action;
    }

}
