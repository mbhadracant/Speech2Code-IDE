package controllers;

import fxelements.EditorStackPane;
import fxelements.JavaCodeArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mb on 07/12/2016.
 */
public class Controller implements Initializable {

    @FXML
    EditorStackPane editorStackPane;
    JavaCodeArea editor;

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
        VirtualizedScrollPane v = (VirtualizedScrollPane) editorStackPane.getChildren().get(0);
        editor = (JavaCodeArea) v.getContent();
    }
}
