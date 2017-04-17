package codeoperations.create.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import org.fxmisc.richtext.model.NavigationActions;
import speechanalyser.Analyser;

import java.util.Map;

/**
 * Created by mb on 22/01/2017.
 */
public class CreateStringAction extends CodeAction {
    String codeToInsert;

    public CreateStringAction() {
        codeToInsert = "\"\"";
    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        try {
            JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
            if(editor == null) return;
            Platform.runLater(() ->  {
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                editor.previousChar(NavigationActions.SelectionPolicy.CLEAR);
                editor.undoStack.add(new JavaCodeArea.TextCaretPair(editor.getText(), editor.getCaretPosition()));
            });

        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
