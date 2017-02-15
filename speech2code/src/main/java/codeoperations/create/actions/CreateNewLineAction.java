package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import utilities.EditorNavigator;

/**
 * Created by mb on 09/02/2017.
 */
public class CreateNewLineAction extends CodeAction {
    String codeToInsert;

    public CreateNewLineAction() {

    }

    @Override
    public void init() {
        StringBuilder template = new StringBuilder();
        template.append("\n");

        for (int i = 0; i < JavaCodeArea.SCOPE_LEVEL-1; i++) {
            template.append("\t");
        }


        codeToInsert = template.toString();
    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(JavaCodeArea editor) {
        try {
            Platform.runLater(() -> {
                EditorNavigator.endLine();
                editor.insertText(editor.getCaretPosition(), codeToInsert);
            });
        } catch (NullPointerException e) {
            System.out.println("null pointer");
        }
    }

}