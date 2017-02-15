package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import utilities.EditorNavigator;

import java.awt.*;

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
    public void init() {
        StringBuilder template = new StringBuilder();
        template.append("public static void main(String[] args) {\n");

        for (int i = 0; i < JavaCodeArea.SCOPE_LEVEL; i++) {
            template.append("\t");
        }

        template.append("\n");

        for (int i = 0; i < JavaCodeArea.SCOPE_LEVEL-1; i++) {
            template.append("\t");
        }

        template.append("}");
        codeToInsert = template.toString();
    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(JavaCodeArea editor) {
        try {
            Platform.runLater(() -> {
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                EditorNavigator.moveUp(1);
                EditorNavigator.endLine();
            });
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
