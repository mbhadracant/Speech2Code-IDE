package codeoperations.create.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import utilities.EditorNavigator;

import java.awt.*;
import java.util.Map;

/**
 * Created by mb on 22/01/2017.
 */
public class CreateMainMethodAction extends CodeAction{

    String codeToInsert;
    Robot r;

    public CreateMainMethodAction() {
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

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
                template.append("public static void main(String[] args) {\n");

                for (int i = 0; i < editor.SCOPE_LEVEL; i++) {
                    template.append("\t");
                }

                template.append("\n");

                for (int i = 0; i < editor.SCOPE_LEVEL-1; i++) {
                    template.append("\t");
                }

                template.append("}");
                codeToInsert = template.toString();
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                EditorNavigator.moveUp(1);
                EditorNavigator.endLine();
                editor.undoStack.add(new JavaCodeArea.TextCaretPair(editor.getText(), editor.getCaretPosition()));
            });
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
