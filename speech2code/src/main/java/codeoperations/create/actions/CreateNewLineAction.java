package codeoperations.create.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import utilities.EditorNavigator;

import java.util.Map;

/**
 * Created by mb on 09/02/2017.
 */
public class CreateNewLineAction extends CodeAction {
    String codeToInsert;

    public CreateNewLineAction() {

    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        try {
            JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
            if(editor == null) return;
            Platform.runLater(() -> {
                StringBuilder template = new StringBuilder();
                template.append("\n");

                for (int i = 0; i < editor.SCOPE_LEVEL-1; i++) {
                    template.append("\t");
                }


                codeToInsert = template.toString();
                EditorNavigator.endLine();
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                editor.undoStack.add(new JavaCodeArea.TextCaretPair(editor.getText(), editor.getCaretPosition()));
            });
        } catch (NullPointerException e) {
            System.out.println("null pointer");
        }
    }

}