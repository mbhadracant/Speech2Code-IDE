package codeoperations.ui.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.Map;

/**
 * Created by mb on 17/04/2017.
 */
public class NextFileAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        Platform.runLater(() -> {
            TabPane tabPane = (TabPane) uiMap.get(Controller.UI.TAB_MENU);
            int selected = tabPane.getSelectionModel().getSelectedIndex();
            try {
                Tab next =  tabPane.getTabs().get(selected + 1);
                tabPane.getSelectionModel().select(next);
            } catch(IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }

        });
    }

}