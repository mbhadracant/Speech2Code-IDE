package codeoperations.create;

import codeoperations.CodeAction;
import codeoperations.create.actions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mb on 12/01/2017.
 */
public class CreateCodeLookup {
    private static CreateCodeLookup instance = null;
    public Map<CreateCodeStatement, CodeAction> actions;

    private CreateCodeLookup() {
        actions = new HashMap<>();
        actions.put(CreateCodeStatement.CLASS, new CreateClassAction());
        actions.put(CreateCodeStatement.MAIN, new CreateMainMethodAction());
        actions.put(CreateCodeStatement.PRINT, new CreatePrintAction());
        actions.put(CreateCodeStatement.PRINT_LINE, new CreatePrintLineAction());
        actions.put(CreateCodeStatement.IF, new CreateIfAction());
        actions.put(CreateCodeStatement.WHILE, new CreateWhileLoopAction());
        actions.put(CreateCodeStatement.FOR, new CreateForLoopAction());
        actions.put(CreateCodeStatement.STRING, new CreateStringAction());
        actions.put(CreateCodeStatement.VALUE, new CreateValueAction());
        actions.put(CreateCodeStatement.NEW_LINE, new CreateNewLineAction());
    }

    private static CreateCodeLookup getInstance() {
        if(instance == null) {
            instance = new CreateCodeLookup();
        }
        return instance;
    }

    public static CodeAction getAction(CreateCodeStatement statement) {
        return getInstance().actions.get(statement);
    }
}
