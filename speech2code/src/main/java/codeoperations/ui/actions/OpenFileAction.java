package codeoperations.ui.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.EditorStackPane;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by mb on 17/04/2017.
 */
public class OpenFileAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        Platform.runLater(() -> {

            TextArea console = (TextArea) uiMap.get(Controller.UI.CONSOLE);
            TabPane tabPane = (TabPane) uiMap.get(Controller.UI.TAB_MENU);

            Tab tab = new Tab("New");
            EditorStackPane editorStackPane = new EditorStackPane();
            tab.setContent(editorStackPane);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);

            JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);

            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JAVA files (*.java)", "*.java");
            fileChooser.getExtensionFilters().add(extFilter);

            Stage stage = (Stage) console.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);

            if(file != null){
                try {
                    String text = FileUtils.readFileToString(file,"UTF-8");
                    editor.clear();
                    editor.insertText(0,text);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                tab.setText(file.getName());
            }
        });
    }

}
