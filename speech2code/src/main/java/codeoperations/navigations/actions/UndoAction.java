package codeoperations.navigations.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;

/**
 * Created by mb on 09/02/2017.
 */
public class UndoAction extends CodeAction {
    @Override
    public void init() {

    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(JavaCodeArea editor) {
        Platform.runLater(() -> {
            editor.undo();
        });
    }
}
