package codeoperations;

import codeoperations.create.actions.*;
import codeoperations.navigations.actions.*;
import codeoperations.ui.actions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mb on 17/04/2017.
 */
public class Lookup {
    private static Lookup instance = null;
    public Map<Statement, CodeAction> actions;

    private Lookup() {
        actions = new HashMap<>();
        actions.put(Statement.CLASS, new CreateClassAction());
        actions.put(Statement.MAIN, new CreateMainMethodAction());
        actions.put(Statement.PRINT, new CreatePrintAction());
        actions.put(Statement.PRINT_LINE, new CreatePrintLineAction());
        actions.put(Statement.IF, new CreateIfAction());
        actions.put(Statement.WHILE, new CreateWhileLoopAction());
        actions.put(Statement.FOR, new CreateForLoopAction());
        actions.put(Statement.STRING, new CreateStringAction());
        actions.put(Statement.VALUE, new CreateValueAction());
        actions.put(Statement.NEW_LINE, new CreateNewLineAction());
        actions.put(Statement.CLASS_NAME, new CreateClassNameAction());
        actions.put(Statement.METHOD_NAME, new CreateMethodNameAction());
        actions.put(Statement.LINE_DOWN, new LineDownAction());
        actions.put(Statement.LINE_UP, new LineUpAction());
        actions.put(Statement.NEXT, new NextAction());
        actions.put(Statement.BACK, new BackAction());
        actions.put(Statement.BACKSPACE, new BackspaceAction());
        actions.put(Statement.UNDO, new UndoAction());
        actions.put(Statement.RUN_PROGRAM, new RunProgramAction());
        actions.put(Statement.CLOSE_FILE, new CloseFileAction());
        actions.put(Statement.SAVE_FILE, new SaveFileAction());
        actions.put(Statement.OPEN_FILE, new OpenFileAction());
        actions.put(Statement.NEXT_FILE, new NextFileAction());
        actions.put(Statement.PREV_FILE, new PreviousFileAction());
        actions.put(Statement.NEW_FILE, new NewFileAction());
        actions.put(Statement.OPEN_HELP, new OpenHelpAction());
        actions.put(Statement.CHAR, new CreateCharAction());
    }

    private static Lookup getInstance() {
        if(instance == null) {
            instance = new Lookup();
        }
        return instance;
        
    }

    public static CodeAction getAction(Statement statement) {
        return getInstance().actions.get(statement);
    }
}
