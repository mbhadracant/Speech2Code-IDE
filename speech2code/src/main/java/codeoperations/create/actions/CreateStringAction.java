package codeoperations.create.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import org.fxmisc.richtext.model.NavigationActions;
import speechanalyser.Analyser;

/**
 * Created by mb on 22/01/2017.
 */
public class CreateStringAction extends CodeAction {
    String codeToInsert;

    public CreateStringAction() {
        codeToInsert = "\"\"";
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
            Platform.runLater(() ->  {
                editor.insertText(editor.getCaretPosition(), codeToInsert);
                editor.previousChar(NavigationActions.SelectionPolicy.CLEAR);
            });

            Analyser.CAN_ONLY_SAY_LETTER_OR_DIGIT = true;
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
