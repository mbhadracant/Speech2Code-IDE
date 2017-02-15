package controllers;

import coderunner.CodeRunner;
import fxelements.EditorStackPane;
import fxelements.JavaCodeArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.fxmisc.flowless.VirtualizedScrollPane;
import speechrecognition.SpeechRecognizer;
import utilities.EditorNavigator;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mb on 07/12/2016.
 */
public class Controller implements Initializable {

    @FXML
    EditorStackPane editorStackPane;
    JavaCodeArea editor;
    @FXML
    TextField commandText;
    @FXML
    Button inputButton;
    @FXML
    TextField inputText;
    @FXML
    CheckBox inputCheckBox;
    @FXML
    Button runButton;
    @FXML
    TextArea console;

    SpeechRecognizer recognizer;
    CodeRunner compiler;

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initializing...");
        console.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        compiler = new CodeRunner(console);
        VirtualizedScrollPane v = (VirtualizedScrollPane) editorStackPane.getChildren().get(0);
        editor = (JavaCodeArea) v.getContent();
        recognizer = new SpeechRecognizer(editor, commandText, inputText, inputButton, inputCheckBox);
        runButton.setOnAction(e -> {
            compiler.set(editor.getText());
            compiler.createJavaFile();
            if(compiler.compile()) {
                compiler.run();
            }
        });

        EditorNavigator.setEditor(editor);

        Thread speechRecognizerThread = new Thread(recognizer);
        speechRecognizerThread.start();

    }





}
