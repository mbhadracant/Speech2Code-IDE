package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;

/**
 * Created by mb on 22/01/2017.
 */
public class CreatePrintAction extends CodeAction {
    String codeToInsert;

    public CreatePrintAction() {
        codeToInsert = "System.out.print();";
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
            Platform.runLater(() -> editor.insertText(editor.getCaretPosition(), codeToInsert));
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
