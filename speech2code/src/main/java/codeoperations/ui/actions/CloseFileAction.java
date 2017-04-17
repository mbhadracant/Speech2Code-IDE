package codeoperations.ui.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import org.fxmisc.richtext.model.NavigationActions;

import java.util.Map;

/**
 * Created by mb on 17/04/2017.
 */
public class CloseFileAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        Platform.runLater(() -> {
            TabPane tabPane = (TabPane) uiMap.get(Controller.UI.TAB_MENU);
            int selected = tabPane.getSelectionModel().getSelectedIndex();
            if(selected >= 0)tabPane.getTabs().remove(selected);
        });
    }

}