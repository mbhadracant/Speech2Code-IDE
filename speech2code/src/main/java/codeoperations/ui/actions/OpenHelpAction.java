package codeoperations.ui.actions;

import codeoperations.CodeAction;
import controllers.Controller;
import fxelements.EditorStackPane;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.HelpData;

import java.util.Map;

/**
 * Created by mb on 18/04/2017.
 */
public class OpenHelpAction extends CodeAction {

    @Override
    public void set(String parameter) {

    }

    @Override
    public void execute(Map<Controller.UI, Node> uiMap) {
        Platform.runLater(() -> {
            TabPane tabPane = (TabPane) uiMap.get(Controller.UI.TAB_MENU);
            Tab tab = new Tab("Documentation");
            TableView table = new TableView();


            TableColumn phraseCol = new TableColumn("Phrase");
            TableColumn descriptionCol = new TableColumn("Description");
            TableColumn beforeActionCol = new TableColumn("Before Action");
            TableColumn afterActionCol = new TableColumn("After Action");

            phraseCol.setCellValueFactory(
                    new PropertyValueFactory<Controller.HelpField, String>("phrase"));

            descriptionCol.setCellValueFactory(
                    new PropertyValueFactory<Controller.HelpField, String>("description"));

            beforeActionCol.setCellValueFactory(
                    new PropertyValueFactory<Controller.HelpField, String>("beforeAction"));

            afterActionCol.setCellValueFactory(
                    new PropertyValueFactory<Controller.HelpField, String>("afterAction"));


            table.getColumns().addAll(phraseCol, descriptionCol, beforeActionCol, afterActionCol);

            table.setItems(HelpData.data);

            table.setPadding(new Insets(10, 0, 0, 10));

            tab.setContent(table);

            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);
        });
    }

}
