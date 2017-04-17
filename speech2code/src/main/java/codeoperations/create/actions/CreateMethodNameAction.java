package codeoperations.create.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import org.apache.commons.lang.StringUtils;
import speechanalyser.Analyser;
import utilities.EditorNavigator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * Created by mb on 12/03/2017.
 */
public class CreateMethodNameAction extends CodeAction {

    String codeToInsert;
    String methodName;
    public HashMap<String, HashSet<String>> classMethodsMap = new HashMap();
    public CreateMethodNameAction() {

    }

    @Override
    public void set(String parameter) {
        methodName = parameter.substring(5,parameter.length());


        String[] words = methodName.split(" ");
        for(int i = 0; i < words.length; i++) {
            if(i != 0) {
                words[i] = StringUtils.capitalize(words[i]);
            }
        }

        codeToInsert = String.join("", words);

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        try {
            JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
            if(editor == null) return;
            Platform.runLater(() -> {
                editor.insertText(editor.getCaretPosition(), "." + codeToInsert + "()");
                EditorNavigator.moveLeft(1);
                editor.undoStack.add(new JavaCodeArea.TextCaretPair(editor.getText(), editor.getCaretPosition()));
            });
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
