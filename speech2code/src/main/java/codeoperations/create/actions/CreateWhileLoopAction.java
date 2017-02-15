package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import speechanalyser.Analyser;
import utilities.EditorNavigator;

/**
 * Created by mb on 22/01/2017.
 */
public class CreateWhileLoopAction extends CodeAction {
    String codeToInsert;

    public CreateWhileLoopAction() {

    }

    @Override
    public void init() {
        StringBuilder template = new StringBuilder();
        template.append("while() {\n");

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
            Platform.runLater(() ->  {
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                EditorNavigator.moveUp(2);
                EditorNavigator.endLine();
                EditorNavigator.moveLeft(3);
            });

        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
