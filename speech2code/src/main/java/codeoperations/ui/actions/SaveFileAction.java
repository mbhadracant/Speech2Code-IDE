package codeoperations.ui.actions;

import codeoperations.CodeAction;
import controllers.Controller;
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
public class SaveFileAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        Platform.runLater(() -> {
            FileChooser fileChooser = new FileChooser();
            TextArea console = (TextArea) uiMap.get(Controller.UI.CONSOLE);
            JavaCodeArea editor = (JavaCodeArea) uiMap.get(Controller.UI.EDITOR);
            TabPane tabPane = (TabPane) uiMap.get(Controller.UI.TAB_MENU);
            if(editor == null) return;

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JAVA files (*.java)", "*.java");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            Stage stage = (Stage) console.getScene().getWindow();
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                try {
                    FileUtils.writeStringToFile(file, editor.getText(), "UTF-8");
                    Tab tab = tabPane.getSelectionModel().getSelectedItem();
                    tab.setText(file.getName());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

}
