package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import speechanalyser.Analyser;
import utilities.EditorNavigator;

/**
 * Created by mb on 22/01/2017.
 */
    public class CreateClassAction extends CodeAction {

    String codeToInsert;

    public CreateClassAction() {

    }

    @Override
    public void init() {
        StringBuilder template = new StringBuilder();
        template.append("public class  {\n");

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
            System.out.println(codeToInsert == null);
            Platform.runLater(() -> {
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                EditorNavigator.moveUp(2);
                EditorNavigator.moveLeft(2);
            });
            Analyser.CAN_ONLY_SAY_LETTER_OR_DIGIT = true;
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
