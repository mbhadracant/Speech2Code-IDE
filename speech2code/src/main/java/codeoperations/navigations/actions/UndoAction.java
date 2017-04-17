package codeoperations.navigations.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;

import java.util.EmptyStackException;
import java.util.Map;

/**
 * Created by mb on 09/02/2017.
 */
public class UndoAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
        if(editor == null) return;
        Platform.runLater(() -> {
            editor.clear();
            try {
                editor.undoStack.pop();
                String text = editor.undoStack.peek().text;
                int caretPosition = editor.undoStack.peek().caretPosition;
                editor.insertText(0, text);
                editor.moveTo(caretPosition);
            } catch (EmptyStackException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
