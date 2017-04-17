package codeoperations.navigations.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.scene.Node;
import org.fxmisc.richtext.model.NavigationActions;

import java.util.Map;

/**
 * Created by mb on 08/02/2017.
 */
public class BackAction extends CodeAction{

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
        if(editor == null) return;
        editor.previousChar(NavigationActions.SelectionPolicy.CLEAR);
    }
}
