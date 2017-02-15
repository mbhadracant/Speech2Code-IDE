package codeoperations.navigations;

import codeoperations.CodeAction;
import codeoperations.navigations.actions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mb on 08/02/2017.
 */
public class NavigationLookup {
    private static NavigationLookup instance = null;
    public Map<NavigationStatement, CodeAction> actions;

    private NavigationLookup() {
        actions = new HashMap<>();
        actions.put(NavigationStatement.LINE_DOWN, new LineDownAction());
        actions.put(NavigationStatement.LINE_UP, new LineUpAction());
        actions.put(NavigationStatement.NEXT, new NextAction());
        actions.put(NavigationStatement.BACK, new BackAction());
        actions.put(NavigationStatement.BACKSPACE, new BackspaceAction());
        actions.put(NavigationStatement.RUN, new RunAction());
        actions.put(NavigationStatement.UNDO, new UndoAction());
    }

    private static NavigationLookup getInstance() {
        if(instance == null) {
            instance = new NavigationLookup();
        }
        return instance;
    }

    public static CodeAction getAction(NavigationStatement statement) {
        return getInstance().actions.get(statement);
    }
}