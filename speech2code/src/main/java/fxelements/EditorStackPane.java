package fxelements;

import javafx.scene.layout.StackPane;
import org.fxmisc.flowless.VirtualizedScrollPane;

/**
 * Created by mb on 07/12/2016.
 */

public class EditorStackPane extends StackPane {

    public EditorStackPane() {
        super(new VirtualizedScrollPane(new JavaCodeArea()));
    }

}