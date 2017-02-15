package utilities;

import fxelements.JavaCodeArea;
import org.fxmisc.richtext.model.NavigationActions;

/**
 * Created by mb on 08/02/2017.
 */
public class EditorNavigator {
    private static EditorNavigator instance;
    private static JavaCodeArea editor;

    private EditorNavigator() {

    }

    private static EditorNavigator getInstance() {
        if(instance == null) instance = new EditorNavigator();
        return instance;
    }

    public static void setEditor(JavaCodeArea codeArea) {
       editor = codeArea;
    }

    public static void moveUp(int n) {
        for(int i = 0; i < n; i++) {
            getInstance().editor.lineStart(NavigationActions.SelectionPolicy.CLEAR);
            getInstance().editor.previousChar(NavigationActions.SelectionPolicy.CLEAR);
        }
    }

    public static void moveLeft(int n) {
        for(int i = 0; i < n; i++) {
            getInstance().editor.previousChar(NavigationActions.SelectionPolicy.CLEAR);
        }
    }

    public static void moveRight(int n) {
        for(int i = 0; i < n; i++) {
            getInstance().editor.nextChar(NavigationActions.SelectionPolicy.CLEAR);
        }
    }

    public static void moveDown(int n) {
        for(int i = 0; i < n; i++) {
            getInstance().editor.lineEnd(NavigationActions.SelectionPolicy.CLEAR);
            getInstance().editor.nextChar(NavigationActions.SelectionPolicy.CLEAR);
        }
    }

    public static void endLine() {
        getInstance().editor.lineEnd(NavigationActions.SelectionPolicy.CLEAR);
    }
}
