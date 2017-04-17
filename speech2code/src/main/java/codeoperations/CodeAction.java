package codeoperations;

import controllers.Controller;
import javafx.scene.Node;
import java.util.Map;

/**
 * Created by mb on 11/01/2017.
 */
public abstract class CodeAction {
    public abstract void set(String parameter);
    public abstract void execute(Map<Controller.UI, Node> uiMap);
}
