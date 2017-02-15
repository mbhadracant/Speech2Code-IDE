package codeoperations.navigations.actions;

import codeoperations.CodeAction;
import fxelements.JavaCodeArea;
import org.fxmisc.richtext.model.NavigationActions;

/**
 * Created by mb on 08/02/2017.
 */
public class NextAction  extends CodeAction {
    @Override
    public void init() {

    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(JavaCodeArea editor) {
        editor.nextChar(NavigationActions.SelectionPolicy.CLEAR);
    }
}