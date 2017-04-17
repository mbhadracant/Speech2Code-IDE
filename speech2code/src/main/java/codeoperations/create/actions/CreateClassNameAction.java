package codeoperations.create.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mb on 12/04/2017.
 */
public class CreateClassNameAction extends CodeAction {
    String codeToInsert;
    public HashMap<String, Class> classMap = new HashMap();

    public CreateClassNameAction() {
    }

    @Override
    public void set(String parameter) {
        codeToInsert = parameter;
    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        try {
            JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
            if(editor == null) return;
            Platform.runLater(() -> {
                String[] words = codeToInsert.split(" ");
                for(int i = 0; i < words.length; i++) {
                    words[i] = StringUtils.capitalize(words[i]);
                }

                String className = String.join("", words);
                int position = editor.getCaretPosition();

                if(classMap.get(codeToInsert).getPackage().getName().contains("util")) {
                    String importStatement = "import " + classMap.get(codeToInsert).getCanonicalName() + ";\n";
                    if(!editor.getText().contains(importStatement)) {
                        editor.insertText(0, importStatement + "\n");
                        editor.moveTo(position + importStatement.length() + 1);
                    }
                }

                editor.insertText(editor.getCaretPosition(), className);
                editor.undoStack.add(new JavaCodeArea.TextCaretPair(editor.getText(), editor.getCaretPosition()));
            });
        } catch(NullPointerException e) {
            System.out.println("null pointer");
        }
    }
}
