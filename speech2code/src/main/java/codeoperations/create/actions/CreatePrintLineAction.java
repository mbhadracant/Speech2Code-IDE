package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;

/**
 * Created by mb on 22/01/2017.
 */
public class CreatePrintLineAction extends CodeAction {
    String codeToInsert;

    public CreatePrintLineAction() {
        codeToInsert = "System.out.println();";
    }

    @Override
    public void init() {

    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(JavaCodeArea editor) {
        try {
            Platform.runLater(() -> {
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                editor.moveTo(editor.getCaretPosition() - 2);
            });
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
