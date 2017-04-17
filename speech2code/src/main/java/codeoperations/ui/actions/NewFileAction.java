package codeoperations.ui.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.EditorStackPane;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.Map;

/**
 * Created by mb on 17/04/2017.
 */
public class NewFileAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        Platform.runLater(() -> {
            TabPane tabPane = (TabPane) uiMap.get(Controller.UI.TAB_MENU);
            Tab tab = new Tab("New");
            EditorStackPane editorStackPane = new EditorStackPane();
            tab.setContent(editorStackPane);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
    }

}
