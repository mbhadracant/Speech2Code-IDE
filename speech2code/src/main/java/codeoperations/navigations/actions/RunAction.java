package codeoperations.navigations.actions;

import codeoperations.CodeAction;
import coderunner.CodeRunner;
import fxelements.JavaCodeArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.fxmisc.richtext.model.NavigationActions;

/**
 * Created by mb on 09/02/2017.
 */


public class RunAction extends CodeAction {
    CodeRunner compiler;
    public RunAction() {
        compiler = new CodeRunner();
    }
    @Override
    public void init() {

    }

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(JavaCodeArea editor) {
        compiler.set(editor.getText());
        compiler.createJavaFile();
        if(compiler.compile()) {
            compiler.run();
        }
    }
}
