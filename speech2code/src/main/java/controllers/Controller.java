package controllers;

import coderunner.CodeRunner;
import fxelements.EditorStackPane;
import fxelements.JavaCodeArea;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fxmisc.flowless.VirtualizedScrollPane;
import speechrecognition.SpeechRecognizer;
import utilities.EditorNavigator;
import utilities.HelpData;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by mb on 07/12/2016.
 */
public class Controller implements Initializable {
    @FXML
    BorderPane root;
    @FXML
    TextField commandText;
    @FXML
    TextArea console;
    @FXML
    MenuItem newFile;
    @FXML
    MenuItem saveFile;
    @FXML
    MenuItem openFile;
    @FXML
    MenuItem closeFile;
    @FXML
    TabPane tabPane;
    @FXML
    MenuItem openHelp;

    JavaCodeArea currentEditor;
    SpeechRecognizer recognizer;
    Map<UI, Node> uiMap;

    public enum UI {
        EDITOR, CONSOLE, TAB_MENU, SPEECH
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initializing...");
        console.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        uiMap = new HashMap();

        uiMap.put(UI.CONSOLE, console);
        uiMap.put(UI.TAB_MENU, tabPane);
        uiMap.put(UI.SPEECH, commandText);

        try {
            recognizer = new SpeechRecognizer(uiMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        newFile.setOnAction(e -> {
            onNewFileButtonClick();
        });

        saveFile.setOnAction(e -> {
            onSaveFileButtonClick();
        });

        openFile.setOnAction(e -> {
            onOpenFileButtonClick();
        });


        tabPane.getSelectionModel().selectedItemProperty().addListener(e -> {
            onTabMenuChange();
        });

        closeFile.setOnAction(e -> {
            onCloseFileButtonClick();
        });

        openHelp.setOnAction(e -> {
            onHelpMenuClick();
        });

        saveFile.setDisable(true);
        closeFile.setDisable(true);

        Thread speechRecognizerThread = new Thread(recognizer);
        speechRecognizerThread.start();

    }

    private JavaCodeArea getEditor(EditorStackPane editorStackPane) {
        VirtualizedScrollPane v = (VirtualizedScrollPane) editorStackPane.getChildren().get(0);
        return (JavaCodeArea) v.getContent();
    }

    private void onSaveFileButtonClick() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JAVA files (*.java)", "*.java");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        Stage stage = (Stage) console.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                FileUtils.writeStringToFile(file, currentEditor.getText(), "UTF-8");
                Tab tab = tabPane.getSelectionModel().getSelectedItem();
                tab.setText(file.getName());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    private void onNewFileButtonClick() {
        Tab tab = new Tab("New");
        EditorStackPane editorStackPane = new EditorStackPane();
        tab.setContent(editorStackPane);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    private void onCloseFileButtonClick() {
        int selected = tabPane.getSelectionModel().getSelectedIndex();
        tabPane.getTabs().remove(selected);
    }

    private void onOpenFileButtonClick() {
        onNewFileButtonClick();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JAVA files (*.java)", "*.java");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = (Stage) console.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                String text = FileUtils.readFileToString(file, "UTF-8");
                currentEditor.clear();
                currentEditor.insertText(0, text);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Tab tab = tabPane.getSelectionModel().getSelectedItem();
            tab.setText(file.getName());
        }
    }

    private void onTabMenuChange() {
        if (tabPane.getTabs().size() > 0) {
            Node content = tabPane.getSelectionModel().getSelectedItem().getContent();
            if (content instanceof TableView) {
                return;
            }

            EditorStackPane editorStackPane = (EditorStackPane) content;
            saveFile.setDisable(false);
            closeFile.setDisable(false);
            currentEditor = getEditor(editorStackPane);
            recognizer.setEditor(getEditor(editorStackPane));
            uiMap.put(UI.EDITOR, currentEditor);
            EditorNavigator.setEditor(getEditor(editorStackPane));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentEditor.requestFocus();
                }
            });
        } else {
            saveFile.setDisable(true);
            closeFile.setDisable(true);
        }
    }

    private void onHelpMenuClick() {
        Tab tab = new Tab("Documentation");
        TableView table = new TableView();


        TableColumn phraseCol = new TableColumn("Phrase");
        TableColumn descriptionCol = new TableColumn("Description");
        TableColumn beforeActionCol = new TableColumn("Before Action");
        TableColumn afterActionCol = new TableColumn("After Action");

        phraseCol.setCellValueFactory(
                new PropertyValueFactory<HelpField, String>("phrase"));

        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<HelpField, String>("description"));

        beforeActionCol.setCellValueFactory(
                new PropertyValueFactory<HelpField, String>("beforeAction"));

        afterActionCol.setCellValueFactory(
                new PropertyValueFactory<HelpField, String>("afterAction"));


        table.getColumns().addAll(phraseCol, descriptionCol, beforeActionCol, afterActionCol);

        table.setItems(HelpData.data);

        table.setPadding(new Insets(10, 0, 0, 10));

        tab.setContent(table);

        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    public static class HelpField {
        private String phrase;
        private String description;
        private String beforeAction;
        private String afterAction;

        public HelpField(String phrase, String description, String beforeAction, String afterAction) {
            this.phrase = phrase;
            this.description = description;
            this.beforeAction = beforeAction;
            this.afterAction = afterAction;
        }

        public String getPhrase() {
            return this.phrase;
        }

        public String getDescription() {
            return this.description;
        }

        public String getBeforeAction() {
            return this.beforeAction;
        }

        public String getAfterAction() {
            return this.afterAction;
        }
    }


}





