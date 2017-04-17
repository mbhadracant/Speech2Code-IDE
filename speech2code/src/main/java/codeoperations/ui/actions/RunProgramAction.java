package codeoperations.ui.actions;

import codeoperations.CodeAction;
import coderunner.CodeRunner;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.fxmisc.richtext.model.NavigationActions;

import java.util.Map;

/**
 * Created by mb on 17/04/2017.
 */
public class RunProgramAction extends CodeAction {

    CodeRunner runner = new CodeRunner();
    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {

        JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
        if(editor == null) return;
        TextArea console = (TextArea) uiMap.get(Controller.UI.CONSOLE);
        runner.setConsole(console);
        runner.set(editor.getText());
        runner.createJavaFile();
        if(runner.compile()) {
            runner.run();
        }
    }
}